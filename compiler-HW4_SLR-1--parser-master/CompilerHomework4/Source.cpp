#include"parser.h"
using namespace std;
 
#define SIZE 100

queue<string> readfile(fstream &fin,parser &SLRparser)
{
	char line[SIZE];
	queue<string> testCases;
    while(fin.getline(line,sizeof(line),'\n')){
		string transfer(line);
		if(!strncmp(line, "#", 1)){
		}
		else if(!strncmp(line, "non-term:", 9)){
			SLRparser.storeNonTerminals(transfer.substr(9));
		}
		else if(!strncmp(line, "term:", 5)){
			SLRparser.storeTerminals(transfer.substr(5));
		}
		else if(!strncmp(line, "prod:", 5)){
			SLRparser.storeProductions(transfer.substr(5));
		}
		else if(!strncmp(line, "start:", 6)){
			SLRparser.storeStartSymbol(transfer.substr(6));
		}
		else{
			testCases.push(transfer);
		}
    }
	return testCases;
}

int main(int argc, char *argv[]){
	fstream fin;
	if ( argc != 2 )
	{
		char line[SIZE];
		cout << "¿é¤JÀÉ®×¸ô®|:";
		cin >> line;
		fin.open(line,ios::in);
	}
	else
	{
		fin.open(argv[1],ios::in);
	}
	parser SLRparser;
	queue<string> testCases = readfile(fin,SLRparser);
	SLRparser.createSLRparser();
	while (!testCases.empty())
	{
		SLRparser.testCases(testCases.front());
		testCases.pop();
	}
	system("pause");
    return 0;
}