#!/usr/bin/env python
import unittest
from BookCrawler import *
testURL = "http://www.amazon.de/Anatomie-Duale-Reihe-Gerhard-Aum%C3%BCller/dp/3131360410/ref=pd_bxgy_b_img_b"
testURLISBN = "978-3131360410"

class TestParsers(unittest.TestCase):
    def setUp(self):
        self.html = downloadUrl(testURL)

    def testISBN(self):
        isbn = getISBN(self.html)
        self.assertEqual(testURLISBN, isbn)
        

__author__="uli"
__date__ ="$19.01.2009 19:52:53$"

if __name__ == '__main__':
    unittest.main()
