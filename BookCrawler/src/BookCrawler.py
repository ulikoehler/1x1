#!/usr/bin/env python
from __future__ import with_statement
import urllib2
import re
import sqlite3
from threading import Thread

__author__="uli"
__date__ ="$19.01.2009 19:52:53$"

#Global regexes
titleRegex = re.compile("<span id=\"btAsinTitle\" >(.+?)</span>", re.L)
isbnRegex = re.compile("<li><b>ISBN-13\\:</b> (\\d{3}-\\d{10})</li>", re.L)
priceRegex = re.compile("<b class=\"priceLarge\">EUR ([1-9,]+)</b>", re.L)
pagesRegex = re.compile("<li><b>[A-Za-z0-9 \\:]+</b> (\\d+) Seiten</li>", re.L)
relatedRegex = re.compile("<a href=\"(http://www.amazon.de[^ <>]+/dp/\\d+[^ <>]*)\"><img src=\"", re.L)

def downloadUrl(url):
    try:
        response = urllib2.urlopen(url)
        return response.read()
    except Exception, detail:
        print "Error: ", detail
        exit(1)

def checkUrl(isbn):
    global conn
    #Check if this book has not yet been crawled
    c = conn.cursor()
    if c.execute('''SELECT COUNT(ISBN) FROM Books WHERE ISBN = ?''', (isbn,)).fetchone()[0] > 0:
        return 1
    return 0

def parseConfigFile():
    with open("config.cfg") as cfg:
        for line in cfg:
            split = line.split("=")
            if split[0] == "num":
                num = int(split[1])
            elif split[0] == "starturl":
                starturl = split[1]
            elif split[0] == "threads":
                threads = int(split[1])
    return (num,starturl,threads)

def getISBN(html):
    match = isbnRegex.search(html)
    return match.group(1)

def getTitle(html):
    match = titleRegex.search(html)
    return match.group(1)

def getPrice(html):
    match = priceRegex.search(html)
    return float(match.group(1).replace(",","."))

def getPageCount(html):
    match = pagesRegex.search(html)
    return int(match.group(1))

def parseURL(url):
    html = downloadUrl(url)
    #Find the ISBN(-13)
    isbn = getISBN(html)
    #Check if this book has not yet been crawled
    if checkUrl(isbn) > 0:
        print "ISBN %s already in database" % isbn
    ##################
    ##Parse the data##
    ##################
    #Find the title
    title = getTitle(html)
    #Find the price
    price = getPrice(html)
    #Find the page count
    pages = getPageCount(html)
    #Insert the book into the database (c is initialized before)
    c = conn.cursor()
    c.execute('''INSERT INTO Books VALUES(?,?,?,?)''', (title, isbn, price, pages))
    #Find related books and download the matches and push them into the HTML queue
    queueAddCounter = 0 #Count the queue add
    matches = relatedRegex.findall(html)
    for match in matches:
        #Add the URL to the queue
        urlQueue.append(match)
        queueAddCounter += 1
    #Inform the user about the parsed book and the relations
    return "Parsed \"%s\"\nAdded %i relations to the queue\n" % (title,queueAddCounter)

class BookCrawlerThread(Thread):
   def run(self):
      #Start the HTML analyzer if there are URLs
    while len(urlQueue) != 0:
        if counter > 0:
            counter -= 1
            #ParseURL returns a message string to concatenate with the "n books to parse" message
            #This is required not to mix the thread outputs
            print parseURL(urlQueue.pop(0)) + "%i books to parse" % counter #pops the oldest entry
        else:
            print "Exiting due to lack of queued relations"
            sys.exit(0)



#####
#Main
#####
if __name__ == "__main__":
    urlQueue = []
    #Read the number of books to read and the start URL from the config file
    config = parseConfigFile()
    counter = config[0]
    url = config[1]
    threads = config[2]
    print "%i books to parse" % counter
    #Download the URL and push it into the queue
    urlQueue.append(url)
    #Init the database
    conn = sqlite3.connect('books.sqlite3')
    conn.execute('''CREATE TABLE IF NOT EXISTS Books(Title VARCHAR(100), ISBN CHAR(14), Price REAL, Pages INTEGER)''')
    #Start the threads
    threadList = []
    for i in xrange(threads):
        t = BookCrawlerThread()
        t.start()
        threadList.append(t)
    for t in threadList:
        t.join()
    conn.close()
    print "Exiting due to counter limit"
    


