/*
 * 一个用于创建四则运算练习题库的类
 * 2018-3-22是
 * 
 * */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class main {
	
	static File file;
	static Writer out;
	static final String Path="D://result.txt";
	static char [] chars=new char [] {'+','-','*','/'};
    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");  
    
    
    /*
     * 主函数
     * 程序入口以及主要 逻辑
     * */
	public static void main(String [] arg0) throws IOException{
		createFile();
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入你想要打印的题目的数量");
		int number=scanner.nextInt();
		int i=0;
		while(i<number){
			String str=getStr();
			int result=getResult(str);
		    if(isTrue(result)){
		    	printToFile(str,result);
		    	i++;
		    }
		}
		out.close();
	}
	
	
	
	/*
	 * 创建储存练习题的答案
	 * 以及文件的初始化
	 * 
	 * */
	
	public static void  createFile(){
		file=new File(Path);
		try {
			out=new FileWriter(file);
			out.append("\r\n201571030310\r\n\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 通过判断练习题的答案是否出现负数来筛选符合需求的练习题
	 * */
	public static boolean isTrue(int result){
		if(result>=0&&result<=100) return true;
		return false;
	}
	
	
	/*
	 * 通过传入的算数运算式字符串，来进行计算，将结果返回
	 * */
	public static int getResult(String str){
		int result=-1;
		try {
			String s1=jse.eval(str)+"";
			float s=Float.valueOf(s1);
			int idx = s1.lastIndexOf(".");//查找小数点的位置
			String strNum=s1;
	        if(idx!=-1) strNum = s1.substring(0,idx);
			if (s1!=null) result=Integer.valueOf(strNum);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/*
	 * 随机生成数学算数运算式字符串
	 * */
	public static String getStr(){
		int n=(int) (Math.random()*4)+1;//随机生成的运算符的个数
		int n1=(int) (Math.random()*3);//随机生成的运算符数组的下标
		int x=(int) (Math.random()*100+1);
		String str=""+x;
		for(int j=0;j<n;j++){
			str+=chars[n1];
			n1=(int) (Math.random()*4);
			x=(int) (Math.random()*100)+1;
			str+=x;
		}
		return str;
	}
	
	
	/*
	 * 将传入的算式以及算式答案进行格式化，将程序里边的*，/，符号更换为课本中的符号
	 * 并将整合后的算式打印输出到指定文件
	 * */
	public static void printToFile(String str,int result) throws IOException{
		str+="=";
		str+=result;
		str=str.replace("*","×");
		str=str.replace("/", "÷");
        out.append(str+"\r\n");
        System.out.println(str+"\r\n");
	}

}
