#include <fstream>
using namespace std;

int m, n;

class CNODE {
public:
    CNODE() { key = false; }
    int list[27];
    bool key;
};

class CTRIE {
public:
    CTRIE() { size=0; }
    void insert( const char *s ) {
        int i = 0, j = 0, k;
        while ( s[i] != '\0' ) {
            k = j;
            if ( !t[j].list[s[i]-'a'] ) {
                t[j].list[s[i]-'a'] = ++size;
                j = size;
            } else
                j = t[j].list[s[i]-'a'];
            i++;
        } 
        t[k].key = true;           
    }
    bool search( const char *s ) {
        int i = 0, j = 0, k;
        while ( s[i] != '\0' ) {
            k = j;
            if ( !t[j].list[s[i]-'a'] )
                return false;
            else
                j = t[j].list[s[i]-'a'];
            i++;
        }
        return t[k].key;
    }
private:
    int size;
    CNODE t[10000];
} T;

int main()
{
    ifstream fin( "trie.in" ); 
    ofstream fout( "trie.out" ); 
    char str[30];
    fin >> n >> m;
    for ( int i = 0; i < n; i++ ) {
        fin >> str;
        T.insert(str);
    } 
    
    for ( int i = 0; i < m; i++ ) {
        fin >> str;
        if ( T.search(str) )
                fout << str << " is in the trie.\n";
	else
		fout << str << " not found.\n";
    }
    return 0;
}
