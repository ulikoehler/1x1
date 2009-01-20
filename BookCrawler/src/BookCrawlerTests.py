#!/usr/bin/env python
from __future__ import with_statement
from BookCrawler import getTitle
from BookCrawler import getPageCount
from BookCrawler import getPrice
from BookCrawler import relatedRegex
from BookCrawler import printIntoTestFile
from BookCrawler import downloadUrl
import unittest
from BookCrawler import *
testURL = "http://www.amazon.de/Anatomie-Duale-Reihe-Gerhard-Aum%C3%BCller/dp/3131360410/ref=pd_bxgy_b_img_b"
testURLISBN = "978-3131360410"
testURLTitle = "Anatomie. Duale Reihe (Broschiert)"
testURLPages = 1344
testURLPrice = 54.95

class TestParserRegex(unittest.TestCase):
    def setUp(self):
        self.html = html

    def testTitle(self):
        title = getTitle(self.html)
        self.assertEqual(title, testURLTitle)

    def testPages(self):
        pages = getPageCount(self.html)
        self.assertEqual(pages, testURLPages)

    def testRelatedRegex(self):
        res = relatedRegex.findall(self.html)
        printIntoTestFile(html)
        self.assertTrue(len(res) > 0, "Result: %s" % res)

    def testPrice(self):
        price = getPrice(self.html)
        self.assertEqual(price, testURLPrice)

    def testISBN(self):
        isbn = getISBN(self.html)
        self.assertEqual(testURLISBN, isbn)
        
__author__="uli"
__date__ ="$19.01.2009 19:52:53$"

if __name__ == '__main__':
    html = downloadUrl(testURL) #Speeds up the test by avoiding to download each time
    unittest.main()
