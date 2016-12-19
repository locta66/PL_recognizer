class MyString : public string
{
public:
    MyString(string s) :string(s) {}
    MyString(const char*a) :string(a) {}
    MyString() {}
    MyString operator()(int, int);
};
MyString MyString::operator()(int a, int b)
{
    return this->substr(a, b);
}