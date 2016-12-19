sort(v.begin(),v.end());
for ( i=v.begin(); i!=v.end(); i++) {
    for (j = i+1; j!=v.end(); j++) {
        if(binary_search(v.begin(),v.end(),Point( j->x, i->y)) &&
           binary_search(v.begin(),v.end(),Point( i->x, j->y)) &&
           (j->x != i->x)&&
           (j->y != i->y)
           )
        {
            nTotalNum++;
        }
    }
}
cout<<nTotalNum/2<<endl;