//
//  main.cpp
//  C++ Practice
//
//  Created by YishengYang
//  Copyright © 2016 Yisheng Yang. All rights reserved.
//

#include <iostream>
#include <deque>
#include <queue>
#include <map>
#include <algorithm>
#include <string>
#include <set>
#include <stack>
#include <sstream>
using namespace std;

int strtoint(string s){
    stringstream ss;
    ss<<s;
    int rv;
    ss>>rv;
    return rv;
}

string inttostring(int n){
    stringstream ss;
    ss<<n;
    string s;
    ss>>s;
    return s;
}

bool isInt(string s){
    if (s.length()>5) {
        return false;
    }
    for (int i=0; i<s.length(); i++) {
        if (s[i] >='0' && s[i] <= '9') {
            continue;
        }
        else
        {
            return false;
        }
    }
    return true;
}

//read an input
//if input is an ins
//if it can be executed,executed it
//else put it into ins stack, set opcount=0
//if next input is oprand, opcount++
//if op is enough, execute top ins
// opcount = opcountstack.top + 1
//else put opcount into opcountstack, set opcount = 0 for new ins
//if still ins in insstack, execute all that has enough oprands
int main()
{
//    freopen("/Users/ysyang/WorkSpace/C++ Practice/C++ Practice/input.txt","r",stdin);
//    freopen("/Users/ysyang/WorkSpace/C++ Practice/C++ Practice/result.txt","w",stdout);
    vector<string> v;
    map<string, int> mp;
    mp["copy"] = 3;
    mp["add"] = 2;
    mp["find"] = 2;
    mp["rfind"] = 2;
    mp["insert"] = 3;
    mp["reset"] = 2;
    mp["print"] = 1;
    mp["printall"] = 0;


    int n;
    cin>>n;
    v.push_back("1");
    string input=" ",s, last_ins, to_execute;
    int opcount=0;
    while (n--) {
        cin>>s;
        v.push_back(s);
    }

    stack<string> insstack;
    stack<int> numopr;
    stack<string> stringopr;
    stack<string> oprands;
    stack<int> opcountstack;

    int op1, op2, op3;
    string t1, t2;
    bool firstInput = true;
    cin>>input;
    while (input!="over") {
        if (mp.find(input)!=mp.end()) {//if input is an ins
            to_execute = input;
            if (mp[input] == 0) {
                //only print all can go here
                for (int i=1; i<v.size(); i++) {
                    cout<<v[i]<<endl;
                }
            }
            else{
                insstack.push(input);
                if (!firstInput) {
                    opcountstack.push(opcount);
                    opcount = 0;
                }
                else{
                    firstInput=false;
                }
            }
        }
        else{//if input is an oprand, add opcount
            opcount++;
            oprands.push(input);
            while (mp[to_execute] == opcount) {
                //exectue
                string pc = to_execute;
                if (pc == "copy") {
                    op3 = strtoint(oprands.top());
                    oprands.pop();
                    op2 = strtoint(oprands.top());
                    oprands.pop();
                    op1 = strtoint(oprands.top());
                    oprands.pop();
                    oprands.push(v[op1].substr(op2,op3));
                }
                if (pc == "add") {
                    t2 = oprands.top();
                    oprands.pop();
                    t1 = oprands.top();
                    oprands.pop();
                    if (isInt(t1) && isInt(t2)) {
                        int sum = strtoint(t1)+strtoint(t2);
                        oprands.push(inttostring(sum));
                    }
                    else{
                        oprands.push(t1+t2);
                    }
                }
                if (pc == "find") {
                    op1 = strtoint(oprands.top());
                    oprands.pop();
                    t1 = oprands.top();
                    oprands.pop();
                    int pos = v[op1].find(t1);
                    if (pos!=string::npos) {
                        oprands.push(inttostring(pos));
                    }
                    else{
                        oprands.push(inttostring(v[op1].length()));
                    }
                }
                if (pc == "rfind") {
                    op1 = strtoint(oprands.top());
                    oprands.pop();
                    t1 = oprands.top();
                    oprands.pop();
                    int pos = v[op1].rfind(t1);
                    if (pos!=string::npos) {
                        oprands.push(inttostring(pos));
                    }
                    else{
                        oprands.push(inttostring(v[op1].length()));
                    }
                }
                if (pc == "insert") {
                    op2 = strtoint(oprands.top());
                    oprands.pop();
                    op1 = strtoint(oprands.top());
                    oprands.pop();
                    t1 = oprands.top();
                    oprands.pop();
                    v[op1].insert(op2, t1);
                }
                if (pc == "reset") {
                    op1 = strtoint(oprands.top());
                    oprands.pop();
                    t1 = oprands.top();
                    oprands.pop();
                    v[op1] = t1;
                }
                if (pc == "print") {
                    op1 = strtoint(oprands.top());
                    oprands.pop();
                    cout<<v[op1]<<endl;
                }
                if (pc == "printall") {
                    for (int i=1; i<v.size(); i++) {
                        cout<<v[i]<<endl;
                    }
                }
                if (insstack.size() > 1) {
                    insstack.pop();
                    opcount = opcountstack.top()+1;
                    opcountstack.pop();
                    to_execute = insstack.top();
                }
                else{
                    while (!insstack.empty()) {
                        insstack.pop();
                    }
                    while (!opcountstack.empty()) {
                        opcountstack.pop();
                    }
                    opcount = 0;
                    firstInput = true;

                }
            }
        }
        cin>>input;

    }
    return 0;
}
