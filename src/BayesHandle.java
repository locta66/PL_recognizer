/**
 * Created by yingyue on 2016/12/17.
 */
import classifier.Classifier;
import classifier.bayes.BayesClassifier;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

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
           /*
            if(!(language_name.charAt(0)=='C'||language_name.charAt(0)=='J'))
            {
                continue;
            }
            */

           if(!language_name.equals("C")&&!language_name.equals("C++")&&!language_name.equals("C#")&&!language_name.equals("Java")&&!language_name.equals("Python")){
                continue;
            }

            if(!tempList[i].isDirectory()){
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

    public void test(){
        String path = "test/";
        File file = new File(path);
        File[] tempList = file.listFiles();
        for(int i =0; i <tempList.length; i ++){
            FileInputStream e1 = null;
            try {
                e1 = new FileInputStream(tempList[i]);
                BufferedReader br = new BufferedReader(new InputStreamReader(e1));
                String line = null;
                StringBuffer buffer = new StringBuffer();

                while((line = br.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                Tokenizer tokenizer = new Tokenizer();
                tokenizer.setToTokens(buffer.toString());
                tokenizer.preProcess();
                tokenizer.ToTokens();
                //String[] TempTest = buffer.toString().split(splitT);
                String[] unknownText =  tokenizer.getTokens();
                System.out.print(tempList[i].getPath()+"            ");
                System.out.println( // will output "positive"
                        this.bayes.classify(Arrays.asList(unknownText)).getCategory());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    public static void main(String[] args){
      BayesHandle bayesHandle =  new BayesHandle();
      Set<String>ss =bayesHandle.bayes.getCategories();
      float posibility =  bayesHandle.bayes.featureProbability("iostream","C++");
         posibility =  bayesHandle.bayes.featureWeighedAverage("iostream","C++");
       System.out.println("posibility:"+posibility);
        String unknown1  = "include cout endl ";
        String unknown2  = "import java import java Iamdonfjals";
        String unknown3  ="\n" +
                "def setup():\n" +
                "    size(640, 360, P3D)\n" +
                "    fill(204)\n" +
                "\n" +
                "\n" +
                "def draw():\n" +
                "    lights()\n" +
                "    background(0)\n" +
                "\n" +
                "    # Change height of the camera with mouseY\n" +
                "    camera(30.0, mouseY, 220.0,  # eyeX, eyeY, eyeZ\n" +
                "           0.0, 0.0, 0.0,        # centerX, centerY, centerZ\n" +
                "           0.0, 1.0, 0.0)        # upX, upY, upZ\n" +
                "\n" +
                "    noStroke()\n" +
                "    box(90)\n" +
                "    stroke(255)\n" +
                "    line(-100, 0, 0, 100, 0, 0)\n" +
                "    line(0, -100, 0, 0, 100, 0)\n" +
                "    line(0, 0, -100, 0, 0, 100)\n" +
                "\n";
        /*Tokenizer tokenizer = new Tokenizer();
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
        Set<String> ss =  bayesHandle.bayes.getFeatures();

        */
        bayesHandle.test();
    }



}
