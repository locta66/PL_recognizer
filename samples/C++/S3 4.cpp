class Array2{
private:
    int** ptr;
    int r,c;
public:
    class Array1{
    public:
        Array1(int* _array):arr1(_array){
        }
        int& operator[](int sub){
            return arr1[sub];
        }
        
    private:
        int* arr1;
    };
    Array2(int a, int b):r(a),c(b){
        ptr = new int* [r];
        for (int i=0; i<c; i++) {
            ptr[i] = new int [c];
        }
        }
    Array2(){
        ptr = NULL;
        r = c = 0;
    }

    Array2 operator= (const Array2& a2){
        if (!a2.ptr) {
            ptr = NULL;
            r = c = 0;
            return *this;
        }
        if (ptr) {
            delete ptr;
        }
        ptr = new int* [a2.r];
        for (int i=0; i<a2.r; i++) {
            ptr[i] = a2.ptr[i];
        }
        r = a2.r;
        c = a2.c;
        return *this;
    }
    
    Array1 operator[](int sub){
        return Array1(ptr[sub]);
    }
    
    int& operator() (int i, int j){
        return ptr[i][j];
    }
};
