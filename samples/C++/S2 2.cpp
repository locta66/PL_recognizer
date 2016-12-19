template <class T>
class  CMyistream_iterator:public iterator<output_iterator_tag, T>{
private:
    istream& is;
    T val;
public:
    CMyistream_iterator(istream& i):is(i){is>>val;}
    void operator++(int i){is>>val;}
    T& operator*(){return val;}
};
