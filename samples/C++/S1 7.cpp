void operator=(char *s){
    r = i = 0;
    int k=0;
    while (s[k]!='+') {
        r = r*10 + s[k]-'0';
        k++;
    }
    k++;
    while (s[k]!='i') {
        i = i*10 + s[k]-'0';
        k++;
    }
}