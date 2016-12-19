//
//  main.cpp
//  C++ Practice
//
//  Created by YishengYang
//  Copyright © 2016 Yisheng Yang. All rights reserved.
//

#include <iostream>
using namespace std;
// 在此处补充你的代码
int sum(int *a, int n, int(*sqr)(int)){
    int v=0;
    for (int i=0; i<n; i++) {
        v+=sqr(a[i]);
    }
    return v;
}
int sqr(int n) {
    return n * n;
}
int main() {
    int t, n, a[0x100];
    cin >> t;
    for (int c = 0; c < t; ++c) {
        cin >> n;
        for (int i = 0; i < n; ++i) cin >> a[i];
        cout << sum(a, n, sqr) << endl;
    }
    return 0;
}