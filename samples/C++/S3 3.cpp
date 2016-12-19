//
//  main.cpp
//  C++ Practice
//
//  Created by YishengYang
//  Copyright © 2016 Yisheng Yang. All rights reserved.
//



#include <iostream>
#include <iomanip>
using namespace std;


int main(){
    int n;
    cin>>n;
    cout<<hex<<n<<endl;
    cout<<setw(10)<<setfill('0')<<dec<<n<<endl;
}
