/**
 * Created by yingyue on 2016/12/17.
 */
import classifier.Classifier;
import classifier.bayes.BayesClassifier;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
public class BayesHandle {
    ArrayList<String>language_name;
    String splitT  = "(\\s*\\.\\s*|\\s+)";
    //String splitT ="(\\s|\\.)";
    Tokenizer tokenizer;
    public Classifier<String, String> bayes;
    public BayesHandle() {

        tokenizer = new Tokenizer();
        bayes = new BayesClassifier<String, String>();
        bayes.setMemoryCapacity(1000000);

        String path = "samples/";
        File file = new File(path);
        File[] tempList = file.listFiles();
        for(int i=0;i<tempList.length;i++){
          // System.out.println(tempList[i].getName());
            String language_name = tempList[i].getName();
            if(!(language_name.charAt(0)=='C'||language_name.charAt(0)=='J'))
            {
                continue;
            }
            File[] readFiles =  tempList[i].listFiles();
            for(int j=0; j<readFiles.length;j++){
                if(!readFiles[j].isDirectory()){
                    try {
                        /*
                        FileReader fr = new FileReader(readFiles[j]);
                        BufferedReader br = new BufferedReader(fr);
                        StringBuffer sb = new StringBuffer();
                        while (br.ready()){
                            sb.append(br.readLine());
                        }

                        */


                        FileInputStream e1 = new FileInputStream(readFiles[j]);
                        BufferedReader br = new BufferedReader(new InputStreamReader(e1));
                        String line = null;
                        StringBuffer buffer = new StringBuffer();

                        while((line = br.readLine()) != null) {
                            buffer.append(line + "\n");
                            if(language_name.equals("Java"));
                            // System.out.println(line);
                        }
                        tokenizer.setToTokens(buffer.toString());
                        tokenizer.preProcess();
                        tokenizer.ToTokens();
                        //String[] TempTest = buffer.toString().split(splitT);
                        String[] TempTest =  tokenizer.getTokens();

                        if(language_name.equals("Java")){
                            for(int n =0 ; n<TempTest.length;n++){
                                //System.out.println(TempTest[n]);
                                //System.out.println(br.toString());
                            }
                        }
                        bayes.learn(language_name,Arrays.asList(TempTest));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }

    public static void main(String[] args){
      BayesHandle bayesHandle =  new BayesHandle();
        String unknown1  = "#include <iostream>\n" +
                "#include\"Binary_tree.cpp\"\n" +
                "using namespace std;\n" +
                "template <class T>\n" +
                "void print(T & x){\n" +
                "    \n" +
                "    cout<<x<<\" \";\n" +
                "}\n" +
                "\n" +
                "int main(int argc, const char * argv[]) {\n" +
                "    // insert code here...\n" +
                "    Binary_tree<int>BT;\n" +
                "    int i=0;\n" +
                "    while(i<=50)\n" +
                "    {\n" +
                "        BT.insert(i);\n" +
                "    }\n" +
                "    BT.preorder(print);\n" +
                "    \n" +
                "    cout<<\"going on well\";\n" +
                "    \n" +
                "    return 0;\n" +
                "}\n";
        String unknown2  = "import java import java Iamdonfjals";
        String unknown3  ="using System.Collections.Generic;\n" +
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
                "}";
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.setToTokens(unknown1.toString());
        tokenizer.preProcess();
        tokenizer.ToTokens();
        //String[] TempTest = buffer.toString().split(splitT);
        String[] unknownText1 =  tokenizer.getTokens();

        tokenizer.setToTokens(unknown2.toString());
        tokenizer.preProcess();
        tokenizer.ToTokens();
        //String[] TempTest = buffer.toString().split(splitT);
        String[] unknownText2 =  tokenizer.getTokens();
        tokenizer.setToTokens(unknown3.toString());
        tokenizer.preProcess();
        tokenizer.ToTokens();
        //String[] TempTest = buffer.toString().split(splitT);
        String[] unknownText3 =  tokenizer.getTokens();
        /*
        final String[] unknownText1 = ("#include <iostream>\n" +
                "using namespace std;\n").split(bayesHandle.splitT);

        final String[] unknownText2 = ("import java import java Iamdonfjals").split(bayesHandle.splitT);
        final String[] unknownText3 = ("using System.Collections.Generic;\n" +
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
                "}").split(bayesHandle.splitT);


                */
        for(int i =0;i<unknownText3.length;i++){
            System.out.println(unknownText3[i]);
        }
        System.out.println("***********");

        System.out.println(bayesHandle.bayes.featureCount(".", "C#"));
        System.out.println( // will output "positive"
                bayesHandle.bayes.classify(Arrays.asList(unknownText1)).getCategory());
        System.out.println( // will output "negative"
                bayesHandle.bayes.classify(Arrays.asList(unknownText2)).getCategory());
        System.out.println( // will output "negative"
                bayesHandle.bayes.classify(Arrays.asList(unknownText3)).getCategory());
    }



}
