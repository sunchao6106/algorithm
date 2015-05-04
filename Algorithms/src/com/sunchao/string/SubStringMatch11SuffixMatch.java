package com.sunchao.string;

public class SubStringMatch11SuffixMatch {
	
	public static void stringSuffixMatch(String text,String pattern){
		
		int tLen = text.length();
		
		int pLen = pattern.length();
		
		int j = 0,i;
		
		int flag = 0;
		
		while(j <=  (tLen-pLen)) 
		{
			for( i = (pLen-1) ; i >= 0 && text.charAt(i+j) == pattern.charAt(i) ; i--){}
			
			if(i < 0)
			{
				++flag;
				System.out.println("^#^ the " + flag +"'s match success!  the index =>" + (j+1));
			}
			
				j++;
		}
		if(flag == 0) System.out.println("^#^ match failed!");
	}
	
	public static void main(String args[])
	{
		
		String text = "how is herohero? hero";
		String pattern ="hero";
		stringSuffixMatch(text,pattern);
	}

}
