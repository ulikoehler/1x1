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
    url = raw_input("Start URL: ")
    #Init the database
    conn = sqlite3.connect(":memory") #//TODO change to a file and find a way to check if the table already exists
    conn.execute('''CREATE TABLE Books(Name VARCHAR(100), ISBN VARCHAR(100), Price REAL, Pages INTEGER)''')
    #Read the URL content
    try:
        response = urllib2.urlopen(url)
        html = response.read()
    except Exception, detail:
        print "Error: ", detail
        exit(1)

    print "Finished downloading"

    urlQueue = []
    htmlQueue = []

    #Find the book price
    titleRegex = re.compile("<span id=\"btAsinTitle\" >[:alnum:]+</span>")
    isbnRegex = re.compile("<li><b>ISBN-13:</b> [1-9]{3,3}-[1-9]{10,10}</li>")
    priceRegex = re.compile("<b class=\"priceLarge\">EUR [1-9,]+</b>")
    
def parseUrl():
    #Find the ISBN(-13)
    match = isbnRegex.search(html)
    matchStart = match.start() + len("<li><b>ISBN-13:</b> ")
    matchEnd = match.end() - len("</li>")
    isbn = html[matchStart:matchEnd]
    #Check if this book has not yet been crawled
    c = conn.cursor()
    
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
    #Insert the book into the database (c is initialized before)
    c.execute('''INSERT INTO Books VALUES(''')
