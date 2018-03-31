package com.example.a22914.softwaretest3.util;

import java.util.Stack;

public class Calculate {
	
	
	private static Calculate calculate;
	private static Stack<String> number;
	private static Stack<String> symbel;
	private static boolean isF=false;
	private static Fraction fraction;
	
	
	public static void main(String [] agrs) { 
		Calculate c=Calculate.getElement();
		String s =c.getResult("(9+1)÷2*5");
		System.out.println(s);
	}
	
	
	/*
	 * ˽�еĹ��췽����������������
	 * */
	private Calculate() {
		number=new Stack<String>();
		symbel=new Stack<String>();
	}
	
	public static Calculate getElement() {
		if(calculate!=null) {
			return calculate;
		}else {
			return new Calculate();
		}
	}

	/*此方法用来判断即将入栈的元素sy2和此时的栈顶元素sy1的优先级
	 * sy1,sy2分别是栈顶元素和即将入栈的元素
	 * */
	private static boolean judge(String sy1,String sy2) {
		if(sy2.equals("(")||sy1.equals("(")) {
			return true;
		}
		if(sy2.equals("+")||sy2.equals("-")) {
			return false;
		}else {
			if(sy1.equals("+")||sy1.equals("-")) {
				return true;
			}
			return false;
		}
	}
	
	
	public static String getResult(String str) {
		String s="";
		Stack nu=new Stack<String>();//Stroge number
		Stack sy=new Stack<String>();
		char [] chars=str.toCharArray();
		for(int i=0;i<str.length();i++) {
			if(chars[i]!='+'&&chars[i]!='-'&&chars[i]!='*'&&chars[i]!='/'
					&&chars[i]!='('&&chars[i]!=')'&&chars[i]!='÷') {
				s+=chars[i];
			}else if(chars[i]=='/') {
				if(!s.equals("")) number.push(s);
				number.push(chars[i]+"");
				s="";
			}else if(chars[i]!=')'){
				if(!s.equals("")) number.push(s);
				if(symbel.isEmpty()||judge(symbel.peek(),chars[i]+"")) {
					 symbel.push(chars[i]+"");
				}else{
					System.out.println(symbel.peek());
					while(!symbel.empty()&&!judge(symbel.peek(),chars[i]+"")) {
						number.push(symbel.pop());
					}
					symbel.push(chars[i]+"");
				}
				s="";
			}else {
				number.push(s);
				s="";
				String s1=symbel.pop();
				while(!s1.equals("(")&&!symbel.isEmpty()) {
					number.push(s1);
					s1=symbel.pop();
					}
				}
			}
		if(!s.equals("")) number.push(s);
		if(!symbel.isEmpty()) {
			while(!symbel.isEmpty()) {
				number.push(symbel.pop());
			}
		}
		System.out.println("==================开始打印后缀表达式====================");
		for(int i=0;i<number.size();i++) {
			System.out.println(number.get(i));
		}
		System.out.println("==================结束打印后缀表达式====================");
		return getResult(number);
	}
	
	private static Stack<String> count(Stack<String> result,boolean isF,int i) {
		if(!isF) {
			switch(i) {
			case 1:
				String str1=Integer.parseInt(result.pop())+Integer.parseInt(result.pop())+"";
				result.push(str1);
				break;
			case 2:
				String str2=-Integer.parseInt(result.pop())+Integer.parseInt(result.pop())+"";
				result.push(str2);
				break;
			case 3:
				String str3=Integer.parseInt(result.pop())*Integer.parseInt(result.pop())+"";
				result.push(str3);
				break;
			case 4:
				int x=Integer.parseInt(result.pop());
				String str4=Integer.parseInt(result.pop())/x+"";
				result.push(str4);
				break;
			}
		}else {
			switch(i) {
			case 1:
				if(fraction!=null) {
					int x1=Integer.parseInt(result.pop());
					int x2=Integer.parseInt(result.pop());
					Fraction f1=new Fraction(x2,x1);
					fraction=fraction.plus(f1);
				}else {
					int x1=Integer.parseInt(result.pop());
					int x2=Integer.parseInt(result.pop());
					Fraction f1=new Fraction(x2,x1);
					int y1=Integer.parseInt(result.pop());
					int y2=Integer.parseInt(result.pop());
					Fraction f2=new Fraction(y2,y1);
					fraction=f1.plus(f2);
				}
				break;
			case 2:
				if(fraction!=null) {
					int x1=Integer.parseInt(result.pop());
					int x2=Integer.parseInt(result.pop());
					Fraction f1=new Fraction(x2,x1);
					fraction=fraction.minus(f1);
				}else {
					int x1=Integer.parseInt(result.pop());
					int x2=Integer.parseInt(result.pop());
					Fraction f1=new Fraction(x2,x1);
					int y1=Integer.parseInt(result.pop());
					int y2=Integer.parseInt(result.pop());
					Fraction f2=new Fraction(y2,y1);

				}
				break;
			case 3:
				if(fraction!=null) {
					int x1=Integer.parseInt(result.pop());
					int x2=Integer.parseInt(result.pop());
					Fraction f1=new Fraction(x2,x1);
					fraction=fraction.multiply(f1);
				}else {
					int x1=Integer.parseInt(result.pop());
					int x2=Integer.parseInt(result.pop());
					Fraction f1=new Fraction(x2,x1);
					int y1=Integer.parseInt(result.pop());
					int y2=Integer.parseInt(result.pop());
					Fraction f2=new Fraction(y2,y1);
					fraction=f1.multiply(f2);
				}
				break;
			case 4:
				if(fraction!=null) {
					int x1=Integer.parseInt(result.pop());
					int x2=Integer.parseInt(result.pop());
					Fraction f1=new Fraction(x2,x1);
					fraction=fraction.divide(f1);
				}else {
					int x1=Integer.parseInt(result.pop());
					int x2=Integer.parseInt(result.pop());
					Fraction f1=new Fraction(x2,x1);
					int y1=Integer.parseInt(result.pop());
					int y2=Integer.parseInt(result.pop());
					Fraction f2=new Fraction(y2,y1);
					fraction=f2.divide(f1);
				}
				break;
			}
		}
		return result;
	}
	
	
	
	private static Stack<String> toNumberStack(Stack<String> s){
		Stack<String>numbers=new Stack<String>();
		for(int i=0;i<s.size();i++) {
			if(!s.get(i).equals("+")&&!s.get(i).equals("-")&&!s.get(i).equals("*")&&!s.get(i).equals("/")
					&&!s.get(i).equals("÷")) {
				System.out.print(s.get(i)+",");
				numbers.push(s.get(i));
			}
		}
		return numbers;
	}
	
	private static String getResult(Stack<String> s) {
		isF=false;
		Stack <String> result=new Stack <String>();
		for(int i=0;i<s.size();i++) {
			if(!s.get(i).equals("+")&&!s.get(i).equals("-")&&!s.get(i).equals("*")&&!s.get(i).equals("/")&&!s.get(i).equals("÷")) {
				result.push(s.get(i));
			}else {
				if(s.get(i).equals("+")) {
					if(isF) {
						result=toNumberStack(s);
						result=count(result,isF,1);
					}else {result=count(result,isF,1);}
					
				}else if(s.get(i).equals("-")) {
					if(isF) {
						result=toNumberStack(s);
						result=count(result,isF,2);
					}else {result=count(result,isF,2);}
					
				}else if(s.get(i).equals("*")) {
					if(isF) {
						result=toNumberStack(s);
						result=count(result,isF,3);
					}else {result=count(result,isF,3);}
					
				}else if(s.get(i).equals("÷")) {
					if(isF) {
						result=toNumberStack(s);
						System.out.println("size:"+result.size());
						result=count(result,isF,4);
					}else {
						result=count(result,isF,4);
					}
					}else if(s.get(i).equals("/")){
						isF=true;
					}
				}
				}
		if(isF) {
			String sre=fraction.toString();
			fraction=null;
			return sre;
		}else {
			return result.pop();
		}
	}
}





