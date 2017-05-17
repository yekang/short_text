package data_process;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Felix on 2017/5/8.
 */
public class process_2 {
    public static ArrayList<ArrayList<String>> readfile(String filename)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
        String lineTxt = br.readLine();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
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
        br.close();
        result.add(name_first);
//        result.add(point);
        result.add(name_second);
        result.add(text);
        System.out.println(name_first.size());
        System.out.println(name_second.size());
        System.out.println(point.size());
        System.out.println(text.size());
        return result;
    }
    public static HashMap<String, String> helper(ArrayList<ArrayList<String>> result){
        HashMap<String, String> text = new HashMap<>();
        for (int i = 0; i < result.get(0).size(); i++){
            String test = result.get(0).get(i) + result.get(1).get(i);
            String test_reverse = result.get(1).get(i) + result.get(0).get(i);
            if (text.containsKey(test)){
                String tem = text.get(test) + "\n" +result.get(2).get(i);
                text.remove(test);
                text.put(test, tem);
            }else if (text.containsKey(test_reverse)){
                String tem = text.get(test_reverse) + "\n" + result.get(2).get(i);
                text.remove(test_reverse);
                text.put(test_reverse, tem);
            }else{
                text.put(test, result.get(2).get(i));
            }

        }
        return text;

    }
    public static void writefile(HashMap<String, String > result)throws Exception{
        Set<String> result_key = result.keySet();
        for (String s:result_key
             ) {
            File f = new File("/Users/Felix/Desktop/code/Java_project/841_data_process/src/data_process/out/" + s + ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(result.get(s));
            bw.close();
        }
//        for (int i = 0; i < result.get(0).size(); i++) {
//            String txtname = result.get(0).get(i) + "<->" + result.get(1).get(i);
//            File f = new File("/Users/Felix/Desktop/code/Java_project/841_data_process/src/data_process/out/" + txtname + ".txt");
//            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
//            bw.write(result.get(2).get(i));
//            bw.close();
//        }

    }
//    public static ArrayList<ArrayList<String>> helper(ArrayList<ArrayList<String>> result){
////    public static void helper(ArrayList<ArrayList<String>> result){
//        ArrayList<String> name_first = new ArrayList<>();
//        ArrayList<String> name_second = new ArrayList<>();
//        ArrayList<String> text = new ArrayList<>();
//        ArrayList<ArrayList<String>> result_helper = new ArrayList<>();
//        name_first.add(result.get(0).get(0));
//        name_second.add(result.get(1).get(0));
//        text.add(result.get(2).get(0));
//        int index = 0;
//        while (index < result.get(0).size()){
//            if((name_first.get(name_first.size()-1).equals(result.get(0).get(index))
//                    && name_second.get(name_second.size()-1).equals(result.get(1).get(index))) ||
//                    (name_first.get(name_first.size()-1).equals(result.get(1).get(index)) &&
//                    name_second.get(name_second.size()-1).equals(result.get(0).get(index)))){
////                System.out.println("1");
//                text.add(text.get(text.size()-1) + "\n" + result.get(2).get(index));
//                text.remove(text.get(text.size()-2));
//                index++;
//            }else {
////                System.out.println("2");
//                name_first.add(result.get(0).get(index));
//                name_second.add(result.get(1).get(index));
//                text.add(result.get(2).get(index));
//                index++;
//            }
//        }
//        result_helper.add(name_first);
//        result_helper.add(name_second);
//        result_helper.add(text);
//        System.out.println(name_first);
//        System.out.println(name_second);
//        System.out.println(text.size());
//        return result_helper;
//
//    }
//    public static void writefile(ArrayList<ArrayList<String>> result)throws Exception{
//        for (int i = 0; i < result.get(0).size(); i++) {
//            String txtname = result.get(0).get(i) + "<->" + result.get(1).get(i);
//            File f = new File("/Users/Felix/Desktop/code/Java_project/841_data_process/src/data_process/out/" + txtname + ".txt");
//            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
//            bw.write(result.get(2).get(i));
//            bw.close();
//        }
//
//    }
    public static void main(String[] args)throws Exception{
        ArrayList<ArrayList<String>> result = readfile("/Users/Felix/Desktop/code/Java_project/841_data_process/src/data_process/outputData2.txt");
        HashMap<String, String> result_helper = helper(result);
//        System.out.println(result_helper);
        writefile(result_helper);

    }

}
