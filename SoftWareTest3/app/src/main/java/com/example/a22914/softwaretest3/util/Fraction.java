package com.example.a22914.softwaretest3.util;

public class Fraction {
	 private int c;  
	    private int d;  
	  
	    public Fraction()                   //默认构造函数，分子分母都为1。  
	    {  
	        c=1;  
	        d=1;  
	    }  
	    public Fraction(int a,int b)                //带参数构造函数，判断分母是否为0。  
	    {  
	        if(b==0)  
	        {  
	            System.out.println("分母 不能为0!");  
	            System.exit(0);  
	        }  
	        c=a;  
	        d=b;  
	        selfTrim();  
	    }  
	  
	    public int getNumerator()  
	    {  
	        return c;  
	    }  
	    public int getDinominator()  
	    {  
	        return d;  
	    }  
	  
	    public void selfTrim()  
	    {  
	        int maxCommon=commonDivisor(c,d);       //求出两个数的最大公约数。  
	        c=c/maxCommon;                  //分式为最简。  
	        d=d/maxCommon;  
	        //整理正负号。  
	        if((c>0&&d<0)||(c<0&&d<0))  
	        {  
	            c=-c;  
	            d=-d;  
	        }  
	    }  
	  
	    public String toString()                        //重写tostring().  
	    {  
	        if(c==0||d==1)                          //分母为1 直接输出分子.  
	        {  
	            return Integer.toString(c);  
	        }  
	        return Integer.toString(c)+"/"+Integer.toString(d);     //输出c/d.  
	    }  
	    //----- plus  
	    public Fraction minus(Fraction f2)  
	    {  
	        int newNumerator=c*f2.getDinominator()-d*f2.getNumerator();  
	        int newDinominator=d*f2.getDinominator();  
	  
	        int maxCommon=commonDivisor(newNumerator,newDinominator);  
	        return new Fraction(newNumerator/maxCommon,newDinominator/maxCommon);  
	    }  
	    //---- minus  
	    public Fraction plus(Fraction f2)  
	    {  
	        int newNumerator=c*f2.getDinominator()+d*f2.getNumerator();  
	        int newDinominator=d*f2.getDinominator();  
	  
	        int maxCommon=commonDivisor(newNumerator,newDinominator);  
	        return new Fraction(newNumerator/maxCommon,newDinominator/maxCommon);  
	    }  
	    
	    
	    //----- mutiply  
	    public  Fraction multiply(Fraction f2)                  //两个分数相乘。  
	    {  
	        int newNumerator=c*f2.getNumerator();  
	        int newDinominator=d*f2.getDinominator();  
	  
	        int maxCommon=commonDivisor(newNumerator,newDinominator);  
	        return new Fraction(newNumerator/maxCommon,newDinominator/maxCommon);  
	    }  
	      
	    //-----  divide  
	    public Fraction divide(Fraction f2)  
	    {  
	        if(f2.getNumerator()==0)  
	        {  
	            System.out.println("0不能做除数！");  
	            System.exit(0);  
	        }  
	        Fraction result=new Fraction(c,d);
	        return result.multiply(new Fraction(f2.getDinominator(),f2.getNumerator()));
	    }  
	  
	    //计算2个数的最大公约数。按绝对值计算。  
	    public  int commonDivisor(int x,int y)       //计算2个数的最大公约数。按绝对值计算。  
	    {  
	        if(x==0||y==0)  
	        {  
	            return 1;  
	        }  
	        int x1;               
	        int y1;  
	        x1=(Math.abs(x)>Math.abs(y))?Math.abs(x):Math.abs(y);                //使x1>y1.  
	        y1=(Math.abs(x)>Math.abs(y))?Math.abs(y):Math.abs(x);  
	        int z=1;  
	            while(z!=0)   
	            {  
	            z=x1%y1;  
	                x1=y1;  
	                y1=z;  
	            }   
	        return x1;  
	    }  

}
