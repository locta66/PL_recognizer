//
//  main.cpp
//  C++ Practice
//
//  Created by YishengYang
//  Copyright © 2016 Yisheng Yang. All rights reserved.
//

#include<iostream>
using namespace std;
class Number {
public:
    int num;
    Number(int n=0): num(n) {}
    // 在此处补充你的代码
    Number operator*(const Number& n2){
        return Number(num * n2.num);
    }
     operator int(){
         return num;
    }
};

int main() {
    Number n1(10), n2(20);
    Number n3;n3 = n1*n2;
    cout << int(n3) << endl;
    return 0;
}