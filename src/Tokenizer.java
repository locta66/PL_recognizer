import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by yingyue on 2016/12/18.
 */
public class Tokenizer {
    String toTokens;
    //    String []tokens;
    ArrayList<String> tokenss;
    HashSet<Character> ignored;
    HashSet<Character> included;

    public Tokenizer(){

        tokenss = new ArrayList<String>();
        ignored = new HashSet<Character>();
        included = new HashSet<Character>();
        ignored.add(' ');
        ignored.add('\n');
        ignored.add('\t');
        ignored.add('+');
        ignored.add('-');
        ignored.add('*');
        ignored.add('/');
        ignored.add('(');
        ignored.add(')');
        ignored.add('{');
        ignored.add('}');
        ignored.add('<');
        ignored.add('>');
        ignored.add('!');
        ignored.add('=');
        included.add('@');
        included.add('#');
        included.add('?');
        included.add('_');
        included.add('<');
        included.add('>');
        included.add('*');
        included.add('[');
        included.add(']');


    }
    public void preProcess(){

        toTokens = toTokens.replaceAll("\"(.*?)\"","");
        toTokens = toTokens.replaceAll("/\\*(.*?)\\*/","");
        toTokens = toTokens.replaceAll("\"\"\"(.*?)\"\"\"","");
        toTokens = toTokens.replaceAll("\'\'\'(.*?)\'\'\'","");
        toTokens = toTokens.replaceAll("//(.*?)\\n","");
    }
    public void ToTokens(){
        int begin_index=-1;
        int count = 0;
        for(int i =0 ;i <toTokens.length();i++){

            char ch = toTokens.charAt(i);
            if(Character.isLetter(ch) || included.contains(ch)){
                if(begin_index==-1){
                    begin_index = i;
                }
                continue;
            }
            else{
                if(i==0)
                    continue;
                if(!ignored.contains(ch)&&!(Character.isDigit(ch))){
                    tokenss.add(toTokens.substring(i,i+1));
                }
                if(('A'<=toTokens.charAt(i-1)&&toTokens.charAt(i-1)<='Z')||('a'<=toTokens.charAt(i-1)&&toTokens.charAt(i-1)<='z')){
                    try {
                        String s = toTokens.substring(begin_index, i);
                        //   System.out.println(s);

                        if(s.length()<=20 && s.length()>2) {
                            tokenss.add(s);
                        }
                        begin_index = -1;
                    }
                    catch (Exception e ){
                        System.out.println(e.getMessage());
                    }

                }
            }

        }


    }
    public void setToTokens(String toTokens){
        this.toTokens = toTokens;
    }
    public  String[] getTokens (){
        preProcess();
        ToTokens();
        int maxlen = tokenss.size();
        String[] sa = new String[maxlen];
        Iterator<String> it = tokenss.iterator();
        int cnt = 0;
        while (it.hasNext()){
            sa[cnt++] = it.next();
        }
        tokenss.clear();

        return sa;
    }

    public static  void main (String []args){
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.setToTokens("\"fsfsadfdsfd\"+abcdefg1" +
                "//a bagaa" +
                "/*" +
                "\"\"\"" +
                "python" +
                "\"\"\""+
                "fdsafdsf" +
                "*/" +
                " fd" +
                "fdsfa   sk");
        tokenizer.preProcess();
        tokenizer.ToTokens();
        String []r = tokenizer.getTokens();
        for(int i=0;i<r.length;i++){
            System.out.println(r[i]);
        }
    }

}