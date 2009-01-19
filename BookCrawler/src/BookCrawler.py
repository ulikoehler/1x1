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
    conn = sqlite3.connect('/tmp/example')
    conn.execute('''CREATE TABLE Books(Name VARCHAR(100), ISBN VARCHAR(100), PRICE VARCHAR(100)''')
    #Read the URL content
    try:
        response = urllib2.urlopen(url)
        html = response.read()
    except Exception, detail:
        print "Error: ", detail
        exit(1)

    print "Finished downloading"
    queue = []
    #Find the book price
    titleRegex = re.compile("<span id=\"btAsinTitle\" >[:alnum:]+</span>")
    isbnRegex = re.compile("<li><b>ISBN-13:</b> [1-9]{3,3}-[1-9]{10,10}</li>")
    priceRegex = re.compile("<b class=\"priceLarge\">EUR [1-9,]+</b>")

    def parseUrl():
        #Find the title
        #Find the ISBN
        #Find the price
        match = priceRegex.search(html)
        matchStart = match.start()
        matchEnd = match.end()
        matchStart += len("<b class=\"priceLarge\">EUR ")
        matchEnd -= len("</b>")
        price =  float(html[matchStart:matchEnd].replace(",","."))

