/*
 * һ�����ڴ�������������ϰ������
 * 2018-3-22��
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
     * ������
     * ��������Լ���Ҫ �߼�
     * */
	public static void main(String [] arg0) throws IOException{
		createFile();
		Scanner scanner=new Scanner(System.in);
		System.out.println("����������Ҫ��ӡ����Ŀ������");
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
	 * ����������ϰ��Ĵ�
	 * �Լ��ļ��ĳ�ʼ��
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
	 * ͨ���ж���ϰ��Ĵ��Ƿ���ָ�����ɸѡ�����������ϰ��
	 * */
	public static boolean isTrue(int result){
		if(result>=0&&result<=100) return true;
		return false;
	}
	
	
	/*
	 * ͨ���������������ʽ�ַ����������м��㣬���������
	 * */
	public static int getResult(String str){
		int result=-1;
		try {
			String s1=jse.eval(str)+"";
			float s=Float.valueOf(s1);
			int idx = s1.lastIndexOf(".");//����С�����λ��
			String strNum=s1;
	        if(idx!=-1) strNum = s1.substring(0,idx);
			if (s1!=null) result=Integer.valueOf(strNum);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/*
	 * ���������ѧ��������ʽ�ַ���
	 * */
	public static String getStr(){
		int n=(int) (Math.random()*4)+1;//������ɵ�������ĸ���
		int n1=(int) (Math.random()*3);//������ɵ������������±�
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
	 * ���������ʽ�Լ���ʽ�𰸽��и�ʽ������������ߵ�*��/�����Ÿ���Ϊ�α��еķ���
	 * �������Ϻ����ʽ��ӡ�����ָ���ļ�
	 * */
	public static void printToFile(String str,int result) throws IOException{
		str+="=";
		str+=result;
		str=str.replace("*","��");
		str=str.replace("/", "��");
        out.append(str+"\r\n");
        System.out.println(str+"\r\n");
	}

}
