#include<iostream>
#include<fstream>
#include<string>
#include <vector> 
#include <queue>
#include<ctype.h>
#include<iterator>
#include<map>
using namespace std;
 
enum actionType{shift,reduce,accept,error};

struct item
{
	int state;
	map<int,string> prod;
	map<string,int> shiftPointer;
};

struct itemParsingTable
{
	int state;
	map<string,string> tableContent;
};

class parser
{
public:
	parser();
	~parser();
	void storeNonTerminals(string token);
	void storeTerminals(string token);
	void storeProductions(string prod);
	void storeStartSymbol(string token);
	void createSLRparser();
	void testCases(string input);
private:
	vector<string> nonTerm;
	vector<string> term;
	map<int,string> prod;
	string start;
	map<string,vector<string>> first;
	map<string,vector<string>> follow;
	map<int,item> setsOfItems;
	map<int,itemParsingTable> table;

	void displayProduction();
	void getToken(string &str,string &token);
	string getLeftArrowToken(string str);
	string getRightArrowToken(string str);
	string getNextToken(string str);
	int getTokenLength(string str);
	int compareToken(string str,vector<string> vector);
	bool isTerminal(string check);
	vector<string> vectorAddVector(vector<string> a,vector<string> b);

	void createFirst();
	vector<string> First(string nonTermIter);

	void createFollow();
	vector<string> Follow(string nonTermIter);

	void createLR_automatonGenerator();
	int canonical(map<int,string> node);
	string AnalysisPosition_init(string str);
	string AnalysisPosition_next(string str);

	map<int,string> CLOSURE(map<int,string> I);

	void createLR_parsingTable();

	actionType action(int I,string a);
};
