#!/usr/bin/env python
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
relatedRegex = re.compile("<td align=\"left\" valign=\"top\"><div style=\"width: 166px; height: 190px;\">\n<a href=\"([:alnum:/]+)\"><div class=\"image-title\">", re.L)

def downloadUrl(url):
    try:
        response = urllib2.urlopen(url)
        return response.read()
    except Exception, detail:
        print "Error: ", detail
        exit(1)

def checkUrl(html, isbn):
    global conn
    #Check if this book has not yet been crawled
    if len(conn.execute('''SELECT COUNT(Title) FROM BOOKS WHERE ISBN = ?''', isbn).fetchone()) > 0:
        return 1
    return 0

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

def parseHTML(html):
    #Find the ISBN(-13)
    isbn = getISBN(html)
    #Check if this book has not yet been crawled
    if checkUrl(html, isbn) > 0:
        return 1
    ##################
    ##Parse the data##
    ##################
    counter -= 1
    #Find the title
    title = getTitle(html)
    #Find the price
    price = getPrice(html)
    #Find the page count
    pages = getPageCount(html)
    #Insert the book into the database (c is initialized before)
    c = conn.cursor()
    c.execute('''INSERT INTO Books VALUES(?,?,?,?)''', title, isbn, price, pages)
    #Find related books and download the matches and push them into the HTML queue
    matches =  priceRegex.findall(html)
    for match in matches:
        matchStart = match.start() + len("<td align=\"left\" valign=\"top\"><div style=\"width: 166px; height: 190px;\">\n<a href=\"")
        matchEnd = match.end() - len("\"><div class=\"image-title\">")
        #Download the page and check if the book has already been covered, if not push it into queue
        html = downloadUrl(html[matchStart:matchEnd])
        if not checkUrl(html, getISBN(html)) > 0:
            htmlQueue.append(html)

#####
#Main
#####
if __name__ == "__main__":
    htmlQueue = []
    #Ask for the URL
    counter = int(raw_input("Number of URLs to crawl: "))
    url = raw_input("Start URL: ")
    #Download the URL and push it into the queue
    htmlQueue.append(downloadUrl(url))
    #Init the database
    conn = sqlite3.connect('books.sqlite3')
    if not len(conn.execute("SELECT COUNT(*) FROM sqlite_master WHERE name=\"Books\"").fetchone()) > 0:
        conn.execute('''CREATE TABLE Books(Name VARCHAR(100), ISBN CHAR(14), Price REAL, Pages INTEGER)''')

    #Main queue controller
    #Start the HTML analyzer if there are page-checks left
    while len(htmlQueue) > 0:
        if counter > 0:
            parseHTML(htmlQueue.pop())
            counter -= 1
        else:
            sys.exit(0)
    


