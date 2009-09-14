#include <ctype.h>
#include <stdio.h>

int main(int argc, char** argv)
{
	if(argc < 3)
	{
		printf("Not enough arguments!\n", "r");
		printf("Usage: %s [infile] [outfile]", argv[0]);
		exit(1);
	}
	FILE* fin = fopen(argv[1], "rb");
	FILE* fout = fopen(argv[2], "wb");
	char buffer[4096];
	int sz; //Size to iterate over
	register int i;
	while((sz = fread(buffer, 1, 4096, fin)) != 0)
	{
		i = 0;
		for(;i < sz; i++)
		{
			if(isalpha(buffer[i]))
			{
				fputc(tolower(buffer[i]), fout);
			}
		}
	}		
	fclose(fin);
	fclose(fout);
}