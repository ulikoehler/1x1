import urllib2
import re
import sqlite3

__author__="uli"
__date__ ="$19.01.2009 19:52:53$"

#####
#Main
#####
if __name__ == "__main__":
    htmlQueue = []
    #Ask for the URL
    counter = int(raw_input("Number of URLs to crawl: "))
    url = raw_input("Start URL: ")
    #Download the URL and push it into the queue
    htmlQueue.push()
    #Init the database
    conn = sqlite3.connect('books.sqlite3')
    if not len(con.execute("SELECT COUNT(*) FROM sqlite_master WHERE name=\"Books\"").fetchone()) > 0:
        conn.execute('''CREATE TABLE Books(Name VARCHAR(100), ISBN CHAR(14), Price REAL, Pages INTEGER)''')

    #Find the book price
    titleRegex = re.compile("<span id=\"btAsinTitle\" >[:alnum:]+</span>", re.L)
    isbnRegex = re.compile("<li><b>ISBN-13:</b> [1-9]{3,3}-[1-9]{10,10}</li>", re.L)
    priceRegex = re.compile("<b class=\"priceLarge\">EUR [1-9,]+</b>", re.L)
    pagesRegex = re.compile("<li><b>[:alpha:]+</b> \d+ Seiten</li>", re.L)
    relatedRegex = re.compile("<td align=\"left\" valign=\"top\"><div style=\"width: 166px; height: 190px;\">\n<a href=\"[:alnum:/]+\"><div class=\"image-title\">", re.L)

    #Main queue controller
    #Start the HTML analyzer if there are page-checks left
    while(len(queue > 0)):
        if counter > 0:
            parseUrl(queue.pop())
            counter -= 1
        else:
            sys.exit(0)

def downloadUrl(url):
    try:
        response = urllib2.urlopen(url)
        return response.read()
    except Exception, detail:
        print "Error: ", detail
        exit(1)

def checkUrl(html, isbn)
    global conn
    #Check if this book has not yet been crawled
    if len(conn.execute('''SELECT COUNT(Title) FROM BOOKS WHERE ISBN = ?''', isbn).fetchone()) > 0
        return 1
    return 0

def getISBN(html):
    match = isbnRegex.search(html)
    matchStart = match.start() + len("<li><b>ISBN-13:</b> ")
    matchEnd = match.end() - len("</li>")
    return html[matchStart:matchEnd]

def getTitle(html):
    match = titleRegex.search(html)
    matchStart = match.start() + len("<span id=\"btAsinTitle\" >")
    matchEnd = match.end() - len("</span>")
    return html[matchStart:matchEnd]

def getPrice(html):
    match = priceRegex.search(html)
    matchStart = match.start() + len("<b class=\"priceLarge\">EUR ")
    matchEnd = match.end() - len("</b>")
    return float(html[matchStart:matchEnd].replace(",","."))

def getPageCount(html):
    match = priceRegex.search(html)
    matchStart = match.start() + len("<li><b>[:alpha:]+</b> ")
    matchEnd = match.end() - len(" Seiten</li>")
    return int(html[matchStart:matchEnd])

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
            htmlQueue.push(html)
    


