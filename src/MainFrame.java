import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yingyue on 2016/12/16.
 */
public class MainFrame extends JFrame {
    String current_open_path;
    String current_run_path;
    String current_compile_path;
    String codesToRun;
    JTextArea textArea;
    JTextArea outputArea;
    BayesHandle bayesHandle;
    public MainFrame() throws HeadlessException {
        bayesHandle = new BayesHandle();
        current_open_path = null;
        JPanel jpanel  = new JPanel();
        textArea  = new JTextArea(30,50);
        outputArea  =new JTextArea(10,50);

        jpanel.setLayout(new BorderLayout());
        jpanel.add(new JScrollPane(textArea),BorderLayout.CENTER);
        jpanel.add(new JScrollPane(outputArea),BorderLayout.SOUTH);
        this.add(jpanel);

        JPanel NorthPanel =  new JPanel();
        JButton compileandRun =  new JButton("Compile&run");
        compileandRun.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCodesSelected();
                String type =  bayesHandle.suspectType(codesToRun);
                outputArea.append("判断类型： "+type+"\n"+"output:\n");

                writeTempFiles(type);
                compileProgram(type);


                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                runProgram(type);
            }
        });
        JButton open =  new JButton("open");
        open.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String path =  openJFileChooser("open",0);
              if(path!= null){

                  try {
                      InputStream is  = new FileInputStream(path);
                      BufferedReader br = new BufferedReader(new InputStreamReader(is));
                      String line = null;
                      StringBuffer buffer = new StringBuffer();
                      while((line = br.readLine())!=null){
                          buffer.append(line+"\n");
                      }

                      textArea.setText(buffer.toString());
                      br.close();
                      is.close();

                  } catch (IOException e1) {
                      e1.printStackTrace();
                  }

                  current_open_path = path;



              }
            }
        });


        NorthPanel.add(compileandRun);
        NorthPanel.add(open);
        jpanel.add(NorthPanel,BorderLayout.NORTH);
        this.setSize(800,600);
        this.setVisible(true);
    }
    private boolean compileProgram(String type){

        if(current_open_path == null)
            return false;
        int count =  current_open_path.length();
        for(int i = count-1; i >0 ;i--){
         char a = current_open_path.charAt(i);
            if(a=='.'){
                current_compile_path = current_open_path.substring(0,i);
                break;
            }
        }
        if(current_compile_path!=null){
            String compileCommand = null;
            switch (type){

                case"C++":
                    compileCommand =  "g++ "+current_open_path+" -o "+current_compile_path;
                    break;
                case "Java":
                    compileCommand = "javac "+current_open_path;
                    break;
                default:
                    compileCommand = "g++ "+current_open_path+" -o "+current_compile_path;
            }


            try {
                Process sProgress =  Runtime.getRuntime().exec(compileCommand);
                current_run_path = current_compile_path;
                current_compile_path  = null;
                InputStream in =  sProgress.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line = null;
                StringBuffer buffer = new StringBuffer();
                while((line = br.readLine())!=null){
                    buffer.append(line+"\n");
                }
                outputArea.append(buffer.toString());
                in.close();
                br.close();
                sProgress.destroy();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }

        return false;
    }

    public String getCodesSelected(){

        codesToRun  =  textArea.getSelectedText();
        if(codesToRun==null){
            codesToRun  = textArea.getText();
        }

        return codesToRun;
    }
    public String getCodesToRun(){
        return codesToRun;
    }


    public void writeTempFiles(String type){

        String postfix = null;
        String fileName  = "temp1";

        switch (type){
            case "C++":
                postfix = "cpp";
                break;
            case "Java":
                fileName = getJavaClassName();
                postfix = "java";
                break;
            default:
                postfix = "cpp";
        }

        String fileAddress  = "codes/"+fileName+"." + postfix;

        try {
            File file = new File(fileAddress);
            OutputStream os = new FileOutputStream(file);
            PrintStream printStream = new PrintStream(os);
            printStream.print(codesToRun);
            printStream.close();
            os.close();
            codesToRun = null;

            current_open_path = file.getAbsolutePath();
            System.out.println(current_open_path);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public String getJavaClassName(){
        if(codesToRun!=null){
            String temp = new String(codesToRun);
            Pattern p  = Pattern.compile("(public class\\s[^\\s{]*)");
            Matcher m = p.matcher(codesToRun);
            String result = null;

            m.find();
            result =  m.group();
            result = result.substring(13,result.length());
            System.out.println(result);
            return result;
        }
        return null;
    }
    private void runProgram(String type){
        try {
            String runCommand;
            String name = null;
            String classPath = null;
            for(int i=current_run_path.length()-1;i>0;i--){
             char  ch  = current_run_path.charAt(i);
             if(ch=='/'){
                 name = current_run_path.substring(i+1,current_run_path.length());
                 classPath = current_run_path.substring(0,i+1);
                 break;
             }
            }
            switch (type){

                case"C++":
                    runCommand =  current_run_path;
                    break;
                case "Java":
                    //java -cp /Users/yingyue/Documents/JAVA/Executer/codes/ JHello
                    runCommand = "java -cp "+classPath+" "+name;
                    break;
                default:
                    runCommand =  current_run_path;

            }
            Process sProgress =  Runtime.getRuntime().exec(runCommand);
            InputStream in =  sProgress.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while((line = br.readLine())!=null){
                buffer.append(line+"\n");
            }
            outputArea.append(buffer.toString());
            in.close();
            br.close();
            sProgress.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String openJFileChooser(String name,int  type){
        //String current_directory="src/record/";
        String current_directory   = "/Users/yingyue/Desktop";
        JFileChooser jFileChooser=new JFileChooser();
        File current_directory_file=new File(current_directory);
        jFileChooser.setCurrentDirectory(null);
        if(current_directory_file.exists()){
            jFileChooser.setCurrentDirectory(new File(current_directory));
        }

        jFileChooser.setFileSelectionMode(type);
        jFileChooser.showDialog(new JLabel(), name);

        File file=jFileChooser.getSelectedFile();
        if(file==null){
            return null;
        }
        String temp=file.getAbsolutePath();
        return temp;
    }
}
