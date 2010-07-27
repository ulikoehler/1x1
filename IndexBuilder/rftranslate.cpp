#include <cstdio>
#include <cstdlib>
#include <map>
#include <string>
#include <iostream>
#include <fstream>
#include <boost/circular_buffer.hpp>
#include <boost/foreach.hpp>

#define foreach BOOST_FOREACH

using namespace std;

int main(int argc, char** argv)
{
	if(argc < 3)
	{
		printf("Not enough arguments!\n");
		printf("Usage: %s [infile] [outfile] [n]", argv[0]);
		exit(1);
	}
	map<string, unsigned long> data;
	int n = atoi(argv[3]);
	FILE* fin = fopen(argv[1], "rb");
	//Read the first n characters
	string s;
	boost::circular_buffer<char> cb(n);
	for(int i = 0; i < n; i++)
	{
		cb.push_back(fgetc(fin));
	}
	char c;
	while((c = fgetc(fin)) != EOF)
	{
		s = cb.linearize();
		if(data.count(s) == 0)
		{
			data[s] = 1;
		}
		else
		{
			data[s] += 1;
		}
		cb.push_back(c);
	}

	fclose(fin);
	ofstream fout(argv[2]);
	map<string,unsigned long>::iterator it = data.begin();
	for(;it != data.end();it++)
	{
		fout << (*it).first << ',' << (*it).second << "\n";
	}
	fout.close();
}