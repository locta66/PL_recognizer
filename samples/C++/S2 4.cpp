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
    double n;
    cin>>n;
    cout<<setprecision(5)<<setiosflags(ios::fixed)<<n<<endl;
    cout.unsetf(ios::fixed);
    cout<<setprecision(7)<<setiosflags(ios::scientific)<<n<<endl;
}
