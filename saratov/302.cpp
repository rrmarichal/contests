#include <cstdio>
#include <cstring>
#include <stack>
#include <cctype>

using namespace std;

enum tag { NONE, UP, DOWN };

char text[1001];
stack<tag> tags;

int main()
{
	scanf("%s", text);
	tag actual=NONE;
	int L=strlen(text);
	for (int j=0; j<L; j++)
	{
		if (L-j>=4 && strncmp(text+j, "<UP>", 4)==0)
		{
			actual=UP;
			tags.push(UP);
			j+=3;
		}
		else
		if (L-j>=6 && strncmp(text+j, "<DOWN>", 6)==0)
		{
			actual=DOWN;
			tags.push(DOWN);
			j+=5;
		}
		else
		if (L-j>=5 && strncmp(text+j, "</UP>", 5)==0)
		{
			tags.pop();
			actual=(tags.size()==0)?NONE:tags.top();
			j+=4;
		}
		else
		if (L-j>=7 && strncmp(text+j, "</DOWN>", 7)==0)
		{
			tags.pop();
			actual=(tags.size()==0)?NONE:tags.top();
			j+=6;
		}
		else
		{
			if (actual==NONE)
				printf("%c", text[j]);
			else
			if (actual==DOWN)
				printf("%c", tolower(text[j]));
			else
				printf("%c", toupper(text[j]));
		}
	}
	return 0;
}
