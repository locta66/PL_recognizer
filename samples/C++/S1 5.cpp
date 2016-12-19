template <class T>
class CArray3D {
private:
    T *** arr;
    
public:
    CArray3D(int a, int b, int c){
        arr = new int **[a];
        for (int i=0; i<a; i++) {
            arr[i] = new int*[b];
        }
        for (int i=0; i<a; i++) {
            for (int j=0; j<b; j++) {
                arr[i][j] = new int [c];
            }
        }
    }
    
    class CArray2D{
    private:
        T ** arr2;
    public:
        CArray2D(int ** arr):arr2(arr){}
        
        class CArray1D{
        private:
            int * arr1;
        public:
            CArray1D(int * _arr1):arr1(_arr1){}
            T& operator[](int k){
                return arr1[k];
            }
        };
        
        CArray1D operator[](int j){
            return CArray1D(arr2[j]);
        }
    
        
    };
    
    CArray2D operator[](int i){
        return CArray2D(arr[i]);
    }
};
