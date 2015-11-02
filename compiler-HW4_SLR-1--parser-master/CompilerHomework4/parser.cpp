#include "parser.h"

parser::parser()
{
}

parser::~parser()
{
}

string&   replace_all_distinct(string&   str,const   string&   old_value,const   string&   new_value)   
{   
    for(string::size_type   pos(0);   pos!=string::npos;   pos+=new_value.length())   {   
        if(   (pos=str.find(old_value,pos))!=string::npos   )   
            str.replace(pos,old_value.length(),new_value);   
        else   break;   
    }   
    return   str;   
}   


void parser::storeNonTerminals(string token){
	token = replace_all_distinct(token," ","");
	nonTerm.push_back(token);
	//cout<<token<<endl;
}
void parser::storeTerminals(string token){
	token = replace_all_distinct(token," ","");
	term.push_back(token);
	//cout<<token<<endl;
}
void parser::storeProductions(string prod){
	prod = replace_all_distinct(prod,":","->");
	this->prod[this->prod.size()] = prod;
	//cout<<prod<<endl;
}
void parser::storeStartSymbol(string token){
	token = replace_all_distinct(token," ","");
	start = "S'";
	token = " S' -> "+token;
	this->prod[this->prod.size()] = token;
	nonTerm.push_back("S'");
	//cout<<start<<endl;
}

void parser::displayProduction()
{
	cout<<"--------------------------------"<<endl;
	for (map<int,string>::iterator  iter = prod.begin();iter != prod.end(); ++iter){
		cout<<"Production("<< iter->first <<"):"<< iter->second <<endl;
	}
}

int parser::compareToken(string str,vector<string> vect)
{
	for (vector<string>::iterator iter = vect.begin();iter != vect.end(); ++iter){
		if(!str.compare(*iter))
			return iter->length();
	}
	return 0;
}

int parser::getTokenLength(string str)
{
	string check;
	for (string::iterator str_it = str.begin(); str_it!=str.end();str_it++)
	{
		if (isgraph(*str_it)){
			check+=*str_it;
			if(!check.compare("->"))
				return 2;
			int length = compareToken(check,nonTerm);
			if (length){
				return length;
			}
			length = compareToken(check,term);
			if (length){
				return length;
			}
		}
		else{
			cout<<"error symbol in prod"<<endl;
			system("PAUSE");
		}
	}
	return 0;
}


string parser::getNextToken(string str)
{
	for (string::iterator str_it = str.begin(); str_it!=str.end();){
		if (!isgraph(*str_it)) ++str_it; 
		else{
			int length = getTokenLength(str.substr( distance(str.begin(),str_it) , std::string::npos ));
			if (length)
			{
				return str.substr(distance(str.begin(), str_it),length);
			}
			str_it+=length;
		}
	}
	return NULL;
}

void parser::getToken(string &str,string &token)
{
	token = getNextToken(str);
	str = str.substr(str.find(token)+token.length(),std::string::npos);

}

string parser::getLeftArrowToken(string str)
{
	string token;
	getToken(str,token);
	return token;
}

string parser::getRightArrowToken(string str)
{
	string token;
	getToken(str,token);
	getToken(str,token);
	getToken(str,token);
	return token;
}

bool parser::isTerminal(string check)
{
	if (compareToken(check,term)) return true;
	return false;
}

vector<string> parser::vectorAddVector(vector<string> a,vector<string> b)
{
	for (vector<string>::iterator aIter = a.begin();aIter != a.end(); ++aIter){
		bool isAdd = true;
		for (vector<string>::iterator bIter = b.begin();bIter != b.end(); ++bIter)
			if(!aIter->compare(*bIter))
				isAdd = false;
		if (isAdd)
		{
			b.push_back(*aIter);
		}
	}
	return b;
}

vector<string> parser::First(string nonTermIter)
{
	vector<string> firstSet;
	if (!first[nonTermIter].empty())	
		return first[nonTermIter];	
	for (map<int,string>::iterator prodIter = prod.begin();prodIter != prod.end(); ++prodIter){
		string str(prodIter->second);
		string token ;
		getToken(str,token);
		if(!nonTermIter.compare(token)){
			getToken(str,token); //Ωb¿Y
			getToken(str,token);
			if(nonTermIter.compare(token)){
				if (isTerminal(token))
					firstSet.push_back(token);
				else
				{
					vector<string> anotherfirstSet;
					bool have$ = false;
					do
					{
						anotherfirstSet =  First(token);
						if (str.compare(""))						
							getToken(str,token);
						else							
							break;
						have$ = false;
						for (vector<string>::iterator firstIter = anotherfirstSet.begin();firstIter != anotherfirstSet.end(); ++firstIter)
							if(firstIter->compare("$")){
								have$ = true;
								anotherfirstSet.erase(firstIter);
							}
						firstSet = vectorAddVector(anotherfirstSet,firstSet);
					} while (have$);
					firstSet = vectorAddVector(anotherfirstSet,firstSet);
				}
			}
		}
	}
	first[nonTermIter] = firstSet;
	return firstSet;
}

void parser::createFirst()
{
	cout<<"------------------------------------------------------------"<<endl;
	for (vector<string>::iterator nonTermIter = nonTerm.begin();nonTermIter != nonTerm.end(); ++nonTermIter){
		First(*nonTermIter);
		cout<<"FIRST("<< *nonTermIter <<") = {";
		for (vector<string>::iterator firstIter = first[*nonTermIter].begin() ; firstIter!=first[*nonTermIter].end() ; ++firstIter)
		{
			cout << " '" << *firstIter << "' ";
		}
		cout << "}" << endl;
	}
}

vector<string> parser::Follow(string nonTermIter)
{
	vector<string> followSet;
	if (!follow[nonTermIter].empty())
		return follow[nonTermIter];
	if (!start.compare(nonTermIter))
		followSet.push_back("$");
	for (map<int,string>::iterator prodIter = prod.begin();prodIter != prod.end(); ++prodIter){
		string arrowLeft;
		string arrowRight(prodIter->second);
		string token;
		getToken(arrowRight,arrowLeft);
		getToken(arrowRight,token);
		size_t found = arrowRight.find(nonTermIter);
		if (found != string::npos)
		{
			string str(arrowRight);
			string token;		
			str = arrowRight.substr(found, string::npos);
			getToken(str,token);
			if (!str.compare(""))
				followSet = vectorAddVector(Follow(arrowLeft),followSet);			
			else
			{
				getToken(str,token);
				if (!isTerminal(token)){
					followSet = vectorAddVector(first[token],followSet);
					if (compareToken("$",first[token]))
					{
						followSet = vectorAddVector(Follow(arrowLeft),followSet);
					}
				}
				else
					followSet.push_back(token);
			}
		}
	}
	follow[nonTermIter] = followSet;
	return followSet;
}

void parser::createFollow()
{
	cout<<"------------------------------------------------------------"<<endl;
	for (vector<string>::iterator nonTermIter = nonTerm.begin();nonTermIter != nonTerm.end(); ++nonTermIter){
		Follow(*nonTermIter);
		cout<<"FOLLOW("<< *nonTermIter <<") = {";
		for (vector<string>::iterator followIter = follow[*nonTermIter].begin() ; followIter!=follow[*nonTermIter].end() ; ++followIter)
		{
			cout << " '" << *followIter << "' ";
		}
		cout << "}" << endl;
	}
}


string parser::AnalysisPosition_init(string str)
{
	string token = getRightArrowToken(str);
	str.insert(str.find(token , str.find("->")),".");
	return str;
}
string parser::AnalysisPosition_next(string str)
{
	string str2  = str.substr(str.find(".")+1,string::npos);
	if (!str2.compare(""))
		return str;
	string token = getLeftArrowToken(str2);
	str.insert(str.find(".")+1+str2.find(token)+token.length(),".");
	str.erase(str.find("."),1);
	return str;
}

map<int,string> parser::CLOSURE(map<int,string> I)
{
	int size = 7;
	vector< pair<int,string> > list;
	for (map<int,string>::iterator prodIter = I.begin();prodIter != I.end(); ++prodIter)
		list.push_back(pair<int,string>(prodIter->first,prodIter->second));
	for (int i = 0,listSize = list.size(); i < listSize ;i++)
	{
		string str = list[i].second.substr(list[i].second.find(".")+1, string::npos);
		if(!str.compare(""))
			continue;
		string token;
		getToken(str,token);
		for (map<int,string>::iterator prodIter2 = prod.begin();prodIter2 != prod.end(); ++prodIter2)
			if (!getLeftArrowToken(prodIter2->second).compare(token))
			{
				if (I.find(prodIter2->first)==I.end()){
					I[prodIter2->first] = AnalysisPosition_init(prodIter2->second);
					list.push_back(pair<int,string>(prodIter2->first,AnalysisPosition_init(prodIter2->second)));
					listSize++;
					}
				else if(I[prodIter2->first].compare(AnalysisPosition_init(prodIter2->second))){
					I[size] = AnalysisPosition_init(prodIter2->second);
					list.push_back(pair<int,string>(size++,AnalysisPosition_init(prodIter2->second)));
					listSize++;
				}
			}
	}
	return I;
}
/*
map<int,string> parser::GOTO(map<int,string> I,string X)
{
	map<int,string> J;
	for (map<int,string>::iterator prodIter = I.begin();prodIter != I.end(); ++prodIter){
		string str = prodIter->second.substr(prodIter->second.find(".")+1, string::npos);
		if(!str.compare(""))
			continue;
		string token;
		getToken(str,token);
		if (!token.compare(X))
			J[prodIter->first] = AnalysisPosition_next(prodIter->second);
	}
	return CLOSURE(J);
}
*/
bool map_compare (map<int,string> const &lhs, map<int,string> const &rhs) {
	for (map<int,string>::const_iterator prodIter = lhs.begin();prodIter != lhs.end(); ++prodIter){
		bool result = false;
		for (map<int,string>::const_iterator prodIter2 = rhs.begin();prodIter2 != rhs.end(); ++prodIter2)
			if (!prodIter->second.compare(prodIter2->second))
				result = true;
		if (!result)
			return false;
	}
	return true;
}


int parser::canonical(map<int,string> production)
{
	production = CLOSURE(production);
	for (map<int,item>::iterator prodIter = setsOfItems.begin();prodIter != setsOfItems.end(); ++prodIter)
		if (map_compare(prodIter->second.prod,production))
		{
			return prodIter->first;
		}
	struct item node;
	node.state = setsOfItems.size();
	node.prod = production;
	setsOfItems[node.state] = node;
	map<string,map<int,string>> pointerProd;
	for (map<int,string>::iterator prodIter = production.begin();prodIter != production.end(); ++prodIter)
	{
		string cutProd = prodIter->second.substr(prodIter->second.find(".")+1,string::npos);
		if (cutProd.compare(""))
		{
			string token;
			getToken(cutProd,token);
			map<int,string> nextNodeProd;
			if (pointerProd.find(token) != pointerProd.end())
				nextNodeProd = pointerProd[token];
			nextNodeProd[prodIter->first] = AnalysisPosition_next(prodIter->second);
			pointerProd[token] = nextNodeProd;
		}
	}
	for (map<string,map<int,string>>::iterator prodIter = pointerProd.begin();prodIter != pointerProd.end(); ++prodIter)
		node.shiftPointer[prodIter->first] = canonical(prodIter->second);
	
	setsOfItems[node.state] = node;
	return node.state;
}

void parser::createLR_automatonGenerator()
{
	cout<<"------------------------------------------------------------"<<endl;
	cout<<"# Start production =";
	map<int,string>  startProduction;
	for (map<int,string>::iterator prodIter = prod.begin();prodIter != prod.end(); ++prodIter)
		if (prodIter->second.find(start) != string::npos)
		{
			startProduction[prodIter->first] = AnalysisPosition_init(prodIter->second);
			cout<< startProduction[prodIter->first] <<endl;
			break;
		}
	canonical(startProduction);
	printf("# Item0 [%#p] created\n",&setsOfItems[0]);
	cout<<"# LR(0) automata: " << setsOfItems.size() << " states created." <<endl;

}


void parser::createLR_parsingTable()
{
	for (map<int,item>::iterator prodIter = setsOfItems.begin();prodIter != setsOfItems.end(); ++prodIter)
	{
		item node = prodIter->second;
		itemParsingTable tableState;
		tableState.state = node.state;
		for (map<string,int>::iterator prodIter2 = node.shiftPointer.begin();prodIter2 != node.shiftPointer.end(); ++prodIter2){
			char buffer[20];
			_itoa( prodIter2->second, buffer, 10 );
			string s(buffer);
			if (isTerminal(prodIter2->first))			
				s = "s"+s;
			tableState.tableContent[prodIter2->first] = s;
		}
		for (map<int,string>::iterator prodIter2 = node.prod.begin();prodIter2 != node.prod.end(); ++prodIter2){//nonTerm
			if (prodIter2->second.find(".")==prodIter2->second.size()-1)
			{
				string token = getLeftArrowToken(prodIter2->second);
				for (vector<string>::iterator prodIter3 = follow[token].begin();prodIter3 != follow[token].end();prodIter3++)
				{
					if (!token.compare(start))
					{
						tableState.tableContent[*prodIter3] = "acc";
						break;
					}
					char buffer[20];
					_itoa( prodIter2->first, buffer, 10 );
					string s(buffer);
					s = "r"+s;
					tableState.tableContent[*prodIter3] = s;
				}
			}
		}
		table[prodIter->first] = tableState;
	}

	cout<<"------------------------------------------------------------"<<endl;
	cout<<" STATE |";
	for (vector<string>::iterator prodIter = term.begin();prodIter != term.end(); ++prodIter)
		cout<<" "<<*prodIter<<"  ";
	cout<<" $  |";
	for (vector<string>::iterator prodIter = nonTerm.begin();prodIter != nonTerm.end(); ++prodIter)
		cout<<" "<<*prodIter<<"  ";
	cout<<"\n-------+-------------------------+-----------------"<<endl;
	for (unsigned int i = 0; i < table.size(); i++)
	{
		printf("%6d | ",table[i].state);
		for (vector<string>::iterator prodIter = term.begin();prodIter != term.end(); ++prodIter){
			cout<<table[i].tableContent[*prodIter]<<"  ";
			if (!table[i].tableContent[*prodIter].compare(""))
				cout<<"  ";
		}
		cout<<table[i].tableContent["$"]<<" ";
		if (table[i].tableContent["$"].compare("acc"))
			cout<<" ";
		if (!table[i].tableContent["$"].compare(""))
				cout<<"  ";
		cout<<"|";
		for (vector<string>::iterator prodIter = nonTerm.begin();prodIter != nonTerm.end(); ++prodIter){
			int print = atoi(table[i].tableContent[*prodIter].c_str());
			if(print)	printf("%3d ",print);
		}
		cout<<endl;
	}
	cout<<"-------+-------------------------+-----------------"<<endl;
	cout<<"------------------------------------------------------------"<<endl;

}

void parser::createSLRparser(){
	displayProduction();
	createFirst();
	createFollow();
	createLR_automatonGenerator();
	createLR_parsingTable();
}

actionType parser::action(int I,string a)
{
	string act = table[I].tableContent[a];
	act = act.substr(0,1);
	if (!act.compare("s"))
		return shift;
	else if(!act.compare("r"))
		return reduce;
	else if(!act.compare("a"))
		return accept;
	return error;
}

void parser::testCases(string input){
	cout<<"Input = "<<input<<endl;
	vector<int> stack;
	stack.push_back(0);
	vector<string> symbol;
	int statement = 0;
	string token;
	while (true)
	{
		if (!input.compare(""))
			token = "$";
		else
			token = getLeftArrowToken(input);
		if (action(statement,token) == shift)
		{
			cout<<"Item"<<statement<<", IN="<<token<<":\t";
			string gotoStr = table[statement].tableContent[token].substr(1,string::npos);
			int gotoState = atoi(gotoStr.c_str());
			stack.push_back(gotoState);
			symbol.push_back(token);
			getToken(input,gotoStr);
			statement = gotoState;
			cout<<"shift "<<token<<", push Item"<<gotoState<<endl;
		}
		else if (action(statement,token) == reduce)
		{
			cout<<"Item"<<statement<<", IN="<<token<<":\t";
			string gotoStr = table[statement].tableContent[token].substr(1,string::npos);
			int reduceProd = atoi(gotoStr.c_str());
			string token;
			string prodution = prod[reduceProd];
			getToken(prodution,token);
			string reduceToken = token;
			getToken(prodution,token);
			int popCount = 0;
			for (;prodution.compare("") ; popCount++)
			{
				getToken(prodution,token);
				symbol.pop_back();
				stack.pop_back();
			}
			symbol.push_back(reduceToken);
			int gotoState = atoi(table[stack.back()].tableContent[reduceToken].c_str());
			stack.push_back(gotoState);
			statement = gotoState;
			cout<<"reduce "<<prod[reduceProd]<<", pop "<<popCount<<" item(s), push Item"<<gotoState<<endl;
		}
		else if (action(statement,token) == accept)
		{
			cout<<"Parser accept the input";
			break;
		}
		else
		{
			cout<<"Item"<<statement<<", IN="<<token<<":\t";
			cout<<"no action defined"<<endl;
			cout<<"Parser reject the input";
			break;
		}
		
	}
	cout<<"\n------------------------------------------------------------"<<endl;
}
