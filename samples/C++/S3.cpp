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
using namespace std;

int main()
{
//    freopen("/Users/ysyang/WorkSpace/C++ Practice/C++ Practice/in-4.txt","r",stdin);
//    freopen("/Users/ysyang/WorkSpace/C++ Practice/C++ Practice/result.txt","w",stdout);
    multiset<int> s;
    set<int> aux;
    int n,x;
    string ins;
    cin>>n;
    while (n--) {
        cin>>ins>>x;
        switch (ins[1]) {
            case 'd':
                s.insert(x);
                aux.insert(x);
                cout<<s.count(x)<<endl;
                break;
            case 'e':
                cout<<s.count(x)<<endl;
                s.erase(x);
                break;
            case 's':
                if (aux.count(x)) {
                    cout<<"1 ";
                }
                else
                    cout<<"0 ";
                cout<<s.count(x)<<endl;
                break;
            default:
                break;
        }
    }
}
