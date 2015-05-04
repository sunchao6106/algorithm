package com.sunchao.string;

import java.util.Arrays;

public class SubStringMatch2 {

	/*
	 * Boyer-Moore
	 * BM字符串匹配算法
	 * 好后缀移动规则
	 * 坏字符移动规则
	 * 移动步长 = max(shift(good suffix) + shift(bad char))
	 */
	
	//aplhable_size 字母表的大小，字典表;(A-Z)
	private static int[]  preBMBadChar(String pattern,int alphable_size)
	{
		int pattern_length = pattern.length();
		
		int i ;
		
		int[] BMBadChar = new int[alphable_size];
		
		for( i = 0 ; i < alphable_size ; i++)
		{  //1.坏字符没出现在模式串中，移动距离为模式串长度；
			BMBadChar[i] = pattern_length;
		}
		
		for(i = 0 ; i < pattern_length ; i++)
		{
			//BMBadChar数组索引是对应pattern各个字符的和数组最后一个元素之间的距离
			//2.坏字符出现在模式串中,模式串中最右边的和坏字符相等的字符和最后一个数组元素之间的距离；
			BMBadChar[(pattern.charAt(i) - 'A')] = pattern_length -1 - i;
		}
		
		return BMBadChar;
	}
	
	
	private static int[] suffixes(String pattern)
	{
		
		int pattern_length = pattern.length();
		
		int[] suffixes = new int[pattern_length];
		
		suffixes[pattern_length-1] = pattern_length;
		
		int q;
		
		for(int i = pattern_length-2 ; i >=0 ; i--)
		{
			
			q = i ;
			
			while(q >= 0 && pattern.charAt(q) == pattern.charAt(pattern_length-1 - i +q))
				    --q;
			
			suffixes[i] =i -q ;
		}
		return suffixes;
	}
	 
	
	private static int[]  preBMGoodSuffix(String pattern)
	{
		
		int i;
		
		int[] suffixes = suffixes(pattern);
		
		int pattern_length = pattern.length();
		
		int goodSuffixed[] = new int[pattern_length];
		
		//1. 无子串等于好后缀，也没有前缀等于好后缀的后缀。移动pattern_length
		
		for(i = 0 ; i < pattern_length ; i++ )
		{
			goodSuffixed[i] = pattern_length;
		}
		//2.无子串等于好好后缀，移动等于patternlength - 前缀等于好后缀的后缀的长度；
		//j = 0;
		
		for(i = pattern_length -1 ;  i >= 0 ; --i)
		{
			if(suffixes[i] == i+1) // 前缀的条件 从索引0开始；
			{
				for(int j =0 ; j < pattern_length - 1 - i ; ++j)
					
				  if(goodSuffixed[j] == pattern_length)
					       
						    goodSuffixed[j] = pattern_length -1 -i;
			}
			
		}
 		// 3.子串等于好后缀，    这三者上下组织关系，￥一个更行一个，% 
 		for(i = 0 ; i <= pattern_length -2 ; i++)
		{
			
			goodSuffixed[pattern_length -1 - suffixes[i]] = pattern_length -1 -i;
		}
 		
 		return goodSuffixed;
	}
	
	public static void BMStringMatch(String text,String pattern,int alphable_size)
	{
		 //alphable-size =26  A-Z
	//	int alphable_size = 26;
		int textLen = text.length();
		int patternLen = pattern.length();
		int[] BMBadChar = preBMBadChar(pattern, alphable_size);
		
		int[] BMGoodSuffix = preBMGoodSuffix(pattern);
		
		int i,j = 0;
		
		int flag =0 ;
		while(j <= textLen - patternLen)
		{
		       for(i = patternLen -1 ; i >= 0 && pattern.charAt(i) == text.charAt(i+j) ; i--){
		    	  // flag++;
		       }
		       
		       if(i < 0)
		       {
		    	   System.out.println("the index =>" + j);
		    	   j  += BMGoodSuffix[0];
		       }
		       else{
		    	   
		    	   j += Math.max(BMGoodSuffix[i], BMBadChar[(text.charAt(i+j)-'A')] -(patternLen -i -1));
		       }
		
		}
		
		
	}
	
	
	
	public static void main(String args[]){
		
	//	int[] suffixes = suffixes("bcdbcdaxcdabcd");
		//System.out.println(Arrays.toString(suffixes));
		String text ="HEREISASIMPLELEAMPLE";
		String pattern ="LEPOEALE";
		int alphable_size = 26;
	    BMStringMatch(text, pattern,alphable_size);
		
		int[] BMBadChar = preBMBadChar(pattern, alphable_size);
		System.out.println(Arrays.toString(BMBadChar));
		int[] BMGoodSuffix =preBMGoodSuffix(pattern);
		System.out.println(Arrays.toString(BMGoodSuffix));
	}
}
