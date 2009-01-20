import urllib2
import re
import sqlite3

__author__="uli"
__date__ ="$19.01.2009 19:52:53$"

#####
#Main
#####
if __name__ == "__main__":
    #Ask for the URL
    count = int(raw_input("Number of URLs to crawl: "))
    url = raw_input("Start URL: ")
    #Init the database
    conn = sqlite3.connect(':memory') #//TODO change to a file and find a way to check if the table already exists
    if not len(con.execute("SELECT COUNT(*) FROM sqlite_master WHERE name=\"Books\"").fetchone()) > 0:
        conn.execute('''CREATE TABLE Books(Name VARCHAR(100), ISBN CHAR(14), Price REAL, Pages INTEGER)''')

    urlQueue = []
    htmlQueue = []

    #Find the book price
    titleRegex = re.compile("<span id=\"btAsinTitle\" >[:alnum:]+</span>", re.L)
    isbnRegex = re.compile("<li><b>ISBN-13:</b> [1-9]{3,3}-[1-9]{10,10}</li>", re.L)
    priceRegex = re.compile("<b class=\"priceLarge\">EUR [1-9,]+</b>", re.L)
    pagesRegex = re.compile("<li><b>[:alpha:]+</b> \d+ Seiten</li>", re.L)
    relatedRegex = re.compile("<td align=\"left\" valign=\"top\"><div style=\"width: 166px; height: 190px;\">\n<a href=\"[:alnum:/]+\"><div class=\"image-title\">", re.L)

    #Main queue controller
    while(len(queue > 0)):
        parseUrl(queue.pop())

def checkUrl(html, isbn)
    global conn
    #Check if this book has not yet been crawled
    if len(conn.execute('''SELECT COUNT(Title) FROM BOOKS WHERE ISBN = ?''', isbn).fetchone()) > 0
        return 1
    return 0

def parseUrl(url):
    #Download the HTML page
    try:
        response = urllib2.urlopen(url)
        html = response.read()
    except Exception, detail:
        print "Error: ", detail
        exit(1)
    #Find the ISBN(-13)
    match = isbnRegex.search(html)
    matchStart = match.start() + len("<li><b>ISBN-13:</b> ")
    matchEnd = match.end() - len("</li>")
    isbn = html[matchStart:matchEnd]
    #Check if this book has not yet been crawled
    if checkUrl(html, isbn) > 0:
        return 1
    #Check if there are page-checks left
    if counter == 0:
        sys.exit(0)
    counter -= 1
    #Find the title
    match = titleRegex.search(html)
    matchStart = match.start() + len("<span id=\"btAsinTitle\" >")
    matchEnd = match.end() - len("</span>")
    title = html[matchStart:matchEnd]
    #Find the price
    match = priceRegex.search(html)
    matchStart = match.start() + len("<b class=\"priceLarge\">EUR ")
    matchEnd = match.end() - len("</b>")
    price = float(html[matchStart:matchEnd].replace(",","."))
    #Find the page count
    match = priceRegex.search(html)
    matchStart = match.start() + len("<li><b>[:alpha:]+</b> ")
    matchEnd = match.end() - len(" Seiten</li>")
    pages = int(html[matchStart:matchEnd])
    #Find related books
    matches priceRegex.findall(html)
    for match in matches:
        matchStart = match.start() + len("<td align=\"left\" valign=\"top\"><div style=\"width: 166px; height: 190px;\">\n<a href=\"")
        matchEnd = match.end() - len("\"><div class=\"image-title\">")
        urlQueue.push(matchStart.matchEnd)
    #Insert the book into the database (c is initialized before)
    c = conn.cursor()
    c.execute('''INSERT INTO Books VALUES(?,?,?,?)''', title, isbn, price, pages)


