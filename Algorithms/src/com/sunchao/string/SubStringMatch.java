package com.sunchao.string;

import java.util.Arrays;

public class SubStringMatch {
	
	//native match O((n-m+1)m)
	
	//朴素字符串匹配算法
	//无预处理时间
	public static void nativeStringMatch(String text,String pattern){
		
		int lLen = text.length();
		
		int sLen =pattern.length();
		
		int j = 0 ;
		
		int flag =0;
		
		for(int i = 0 ; i < lLen - sLen + 1 ; i++)
		{
			
			for(j = 0 ; j < sLen ; j++)
			{
			    if(text.charAt(i+j) != pattern.charAt(j)) break;	
			    			
			}
			
			if(j == sLen)
			{
				++flag;
				System.out.println("^~^  match success! " + flag + "'s times, " +"the index is =>" + i);
			}
		}
		
		if(flag == 0)  System.out.println("^#^" + "  match fail");
	}
	/*
	 * RK字符串匹配
	 * O(M)预处理，最坏情况O((n-m+1)m)
	 * 平均O(n+m)  
	 */
	private static boolean isMatch(String pattern,String text,int start,int m){
		
	    int textStart,patternStart;
	    
	    for(textStart = start,patternStart = 0;patternStart < m && textStart < (m+textStart);textStart++,patternStart++)
	    {
	    	if(pattern.charAt(patternStart) != text.charAt(textStart))  return false;
	    	
	    }
		return true;
	}
	
	public static int[]  getNext(final String pattern){
		
		int length = pattern.length();
		int[] next = new int[length];
		next[0] = -1;
		int j =-1;
		int k =0;
		
		while(k < length -1)
		{
			
			if(j == -1 || pattern.charAt(k) == pattern.charAt(j)) // k 在后， j在前
			{
				k++;
				j++;
				next[k] = j;
			}else{
				
				  j = next[j];
			}
			
		}
		return next;
	}
	/*
	 * KMP算法
	 * 
	 */
	public static void kmpStringMatch(String text,String pattern){
		
		int[] next = getNext(pattern);
		
		int textLen = text.length();
		
		int patternLen = pattern.length();
		
		int i = 0, j = 0;
		
		while(i < textLen && j < patternLen)
		{
			
		   if(j == -1 || pattern.charAt(j) ==  text.charAt(i))
		   {
			     i++;
			     j++;
			     
		   }else{
			   
			    j= next[j];
		   }
		}
		if(j == patternLen)  System.out.println("^~^  match success! "  +"the index is =>" + (i -j));
		
		else
			System.out.println("^#^" + "  match fail");
	}
	
	/*
	 * RK字符串匹配 算法
	 * 
	 */
	public static void rkStringMatch(String text,String pattern){
		
		int textLen =text.length();
		
		int patternLen = pattern.length();
		int shift = 1<<(patternLen -1);
		int flag =0;
		int textHash =0;
		int patternHash = 0;
		//preprocess 预处理
		for(int i = 0 ; i < patternLen ; i++)
		{
			patternHash = (patternHash << 1 ) + (pattern.charAt(i)-'a');
			textHash = (textHash << 1) + (text.charAt(i) - 'a');
			
		}
		//查找
		for(int j = 0 ; j < textLen - patternLen ; j++)
		{
			if(textHash == patternHash)
			{
				if(isMatch(pattern, text, j, patternLen))
				{
					++flag;
					System.out.println("^~^  match success! " + flag + "'s times, " +"the index is =>" + j);
				}
			}
			
			textHash =((textHash - (text.charAt(j) - 'a') * shift ) << 1 ) +  (text.charAt(j+patternLen)-'a');//注意这边的J+patternLen,小心越界； 
		}
		
		if(flag == 0)  System.out.println("^#^" + "  match fail");
	}
   /*
    * Finte AutoMaton Machine 
    * 
    * 
    */
	public static void finteAutoMatonStringMatch(String text,String pattern){
		
		
	}
	public static void main(String args[]){
		int[] next = getNext("ababacb");
		System.out.println(Arrays.toString(next));
		String a ="absaabc";
		String b ="aab";
		kmpStringMatch(a,b);
	}
}
