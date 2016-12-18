import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by yingyue on 2016/12/18.
 */
public class Tokenizer {
    String toTokens;
//    String []tokens;
    ArrayList<String> tokenss;
    public Tokenizer(){
        tokenss = new ArrayList<String>();
    }
    public void preProcess(){



    }
    public void ToTokens(){
        int begin_index=-1;
        int count = 0;
        for(int i =0 ;i <toTokens.length();i++){

            char ch = toTokens.charAt(i);
            if(('A'<=ch&&ch<='Z')||('a'<=ch&&ch<='z')){
                if(begin_index==-1){
                    begin_index = i;
                }
                continue;
            }
            else{
                if(i==0)
                    continue;
                if(ch!=' '&&ch!='\t'&&ch!='\n'){
                    tokenss.add(toTokens.substring(i,i+1));
                }
                if(('A'<=toTokens.charAt(i-1)&&toTokens.charAt(i-1)<='Z')||('a'<=toTokens.charAt(i-1)&&toTokens.charAt(i-1)<='z')){
                  try {
                      String s = toTokens.substring(begin_index, i);
                   //   System.out.println(s);
                      tokenss.add(s);
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
        tokenizer.setToTokens("using System.Collections.Generic;\n" +
                "using System.Collections.ObjectModel;\n" +
                "using System.Linq;\n" +
                "using System.Linq.Expressions;\nusing System;\n" +
                "using System.Collections.Generic;\n" +
                "using System.Linq;\n" +
                "using System.Text;\n" +
                "using System.Threading.Tasks;" +
                "namespace MongoDB.Serialization.Descriptors\n" +
                "{\n" +
                "    internal class BsonPropertyValue\n" +
                "    {\n" +
                "        public bool IsDictionary { get; private set; }\n" +
                "\n" +
                "        public Type Type { get; private set; }\n" +
                "\n" +
                "        public object Value { get; private set; }\n" +
                "\n" +
                "        public BsonPropertyValue(Type type, object value, bool isDictionary)\n" +
                "        {\n" +
                "            Type = type;\n" +
                "            Value = value;\n" +
                "            IsDictionary = isDictionary;\n" +
                "        }\n" +
                "    }\n" +
                "}");
        tokenizer.preProcess();
        tokenizer.ToTokens();
        String []r = tokenizer.getTokens();
        for(int i=0;i<r.length;i++){
            System.out.println(r[i]);
        }
    }

}