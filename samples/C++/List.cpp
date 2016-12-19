//
//  main.cpp
//  C++ Practice
//
//  Created by YishengYang
//  Copyright © 2016 Yisheng Yang. All rights reserved.
//

//Coursera 采用的是C++ 98的编译器 
//vector<list<int> >后面两个括号中需要空格
#include <iostream>
#include <list>
#include <vector>
#include <string>
#include <cstring>
#include <cstdlib>

using namespace std;

void printList(const list<int>& lst)
{
    if (lst.empty()) {
        cout<<endl;
    }
    else
    {
        list<int>::const_iterator it = lst.begin();
        while (it != lst.end()) {
            cout<<*it<<" ";
            it++;
        }
        cout<<endl;
    }

}

int main(){
//    freopen("/Users/ysyang/WorkSpace/C++ Practice/C++ Practice/in-4.txt","r",stdin);
//    freopen("/Users/ysyang/WorkSpace/C++ Practice/C++ Practice/result.txt","w",stdout);

    int t;
    vector<list<int> > l(10000+1);
    cin>>t;
    string ins;
    int id1, id2, num;
    while (t--) {
        cin>>ins;
        switch (ins[0]) {
            case 'a':
                cin>>id1>>num;
                l[id1].push_back(num);
                break;
            case 'm':
                cin>>id1>>id2;
                l[id1].merge(l[id2]);
                break;
            case 'n':
                cin>>id1;
                l[id1].clear();
                break;
            case 'o':
                cin>>id1;
                l[id1].sort();
                printList(l[id1]);
                break;
            case 'u':
                cin>>id1;
                l[id1].sort();
                l[id1].unique();
                break;
            default:
                break;
        }
    }
}
