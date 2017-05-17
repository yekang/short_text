package data_process;
//import java.util.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Felix on 2017/5/8.
 */
public class process_1 {
public static ArrayList<String> readtxtfile(String filename)  throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
    String lineTxt = br.readLine();
    ArrayList<String> name_first = new ArrayList<>();
    ArrayList<String> name_second = new ArrayList<>();
    ArrayList<String> point = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();
    String[] tem_1 = lineTxt.split(",");
    name_first.add(tem_1[2]);
    name_second.add(tem_1[4]);
    point.add(tem_1[3]);
    StringBuilder tem_2 = new StringBuilder();
    for (int i = 6; i < tem_1.length;i++) tem_2.append(tem_1[i]);
    text.add(tem_2.toString());
    lineTxt = br.readLine();
    while (lineTxt != null) {
        String[] tem = lineTxt.split(",");
        StringBuilder text_tem = new StringBuilder();
        if (tem[2].equals(name_first.get(name_first.size()-1)) && tem[4].equals(name_second.get(name_second.size()-1))
                && tem[3].equals(point.get(point.size()-1))){
            for(int i = 6; i < tem.length; i++) text_tem.append(tem[i]);
            text.add(text.get(text.size()-1) + "。" + text_tem.toString() + "。");
            text.remove(text.size()-2);
        }else {
            name_first.add(tem[2]);
            name_second.add(tem[4]);
            point.add(tem[3]);
            for (int i = 6; i < tem.length; i++) {
                text_tem.append(tem[i]);
            }
            text.add(text_tem.toString());
        }
        lineTxt = br.readLine();
    }
//    System.out.println("over");
    br.close();
    return  text;
}
    public static void writefile(ArrayList<String> text)throws Exception{
        File f = new File("/Users/Felix/Desktop/code/Java_project/841_data_process/src/data_process/output.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        for (int i = 0; i < text.size(); i++){
            bw.write(text.get(i) + "\n");
        }
        bw.close();
    }
    public static void main(String[] args)throws Exception{
        ArrayList<String> result = readtxtfile("/Users/Felix/Desktop/code/Java_project/841_data_process/src/data_process/outputData2.txt");
        writefile(result);


    }
}
