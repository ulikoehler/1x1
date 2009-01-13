import socket
import urllib

__author__="uli"
__date__ ="$12.01.2009 16:48:52$"

if __name__ == "__main__":
    url = raw_input("URL: ")
    count = int(raw_input("Count: "))
    traffic = 0 #Traffic in bytes
    for i in xrange(count):
        print "Starting %ith download" % (i+1)
        socket = urllib.urlopen(url)
        d = socket.read()
        traffic += len(d)
        print "Finished %ith download" % (i+1)
        print "Traffic: %i bytes" % traffic
    print "Overall traffic: %i bytes" % traffic