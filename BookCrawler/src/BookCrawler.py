#!/usr/bin/env python
from __future__ import with_statement
import urllib2
import re
import sqlite3

__author__="uli"
__date__ ="$19.01.2009 19:52:53$"

#Global regexes
titleRegex = re.compile("<span id=\"btAsinTitle\" >(.+?)</span>", re.L)
isbnRegex = re.compile("<li><b>ISBN-13\\:</b> (\\d{3}-\\d{10})</li>", re.L)
priceRegex = re.compile("<b class=\"priceLarge\">EUR ([1-9,]+)</b>", re.L)
pagesRegex = re.compile("<li><b>[A-Za-z0-9\\:]+</b> (\\d+) Seiten</li>", re.L)
relatedRegex = re.compile("<a href=\"(http://www.amazon.de[^ <>]+/dp/\\d+[^ <>]*)\"><img src=\"", re.L)
#re.compile("<td align=\"left\" valign=\"top\"><div style=\"width: 166px; height: 190px;\">\n
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
    return (num,starturl)

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

#DEBUG
def printIntoTestFile(s):
    with open("test.txt", "w") as o:
        o.write(s)

def parseHTML(html):
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
    print "Parsing \"" + title + "\""
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
        #Download the page and check if the book has already been covered, if not push it into queue
        html = downloadUrl(match) #Match[1] == match.group(1) when findall is used
        if checkUrl(getISBN(html)) == 0:
            htmlQueue.append(html)
            queueAddCounter += 1
        else:
            print "Relation %s already in database" % getTitle(html)
    #Inform the user about how many relations have been added to the database
    print "Added %i relations to the queue" % queueAddCounter


#####
#Main
#####
if __name__ == "__main__":
    htmlQueue = []
    #Read the number of books to read and the start URL from the config file
    config = parseConfigFile()
    counter = config[0]
    url = config[1]
    print "%i books to parse" % counter
    #Download the URL and push it into the queue
    htmlQueue.append(downloadUrl(url))
    #Init the database
    conn = sqlite3.connect('books.sqlite3')
    conn.execute('''CREATE TABLE IF NOT EXISTS Books(Title VARCHAR(100), ISBN CHAR(14), Price REAL, Pages INTEGER)''')
    #Main queue controller
    #Start the HTML analyzer if there are page-checks left
    while len(htmlQueue) > 0:
        if counter > 0:
            parseHTML(htmlQueue.pop())
            print "%i books to parse" % counter
        else:
            print "Exiting due to lack of queued relations"
            sys.exit(0)
    conn.close()
    print "Exiting due to"
    


