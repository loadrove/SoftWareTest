package com.example.a22914.softwaretest3.util;

public class Test {

	public static void main(String[] args) {
		String str = "12+45*55/88";
//		String[]  strs=str.split("");
//		for(int i=0,len=strs.length;i<len;i++){
//		    System.out.println(strs[i].toString());
//		}
		int j=1;
		String s="";
		char [] chars=str.toCharArray();
//		for(char h :chars) {
//			System.out.println(h);
//		}
		for(int i=0;i<str.length();i++) {
			if(chars[i]!='+'&&chars[i]!='-'&&chars[i]!='*'&&chars[i]!='/'
					&&chars[i]!='('&&chars[i]!=')') {
				s+=chars[i];
			}else{
				System.out.println(s);
				s="";
				System.out.println(chars[i]);
			}
		}
		System.out.println(s);
	}

}
