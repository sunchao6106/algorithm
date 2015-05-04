package com.sunchao.string;

import java.util.Arrays;

public class SubStringMatch2 {

	/*
	 * Boyer-Moore
	 * BM�ַ���ƥ���㷨
	 * �ú�׺�ƶ�����
	 * ���ַ��ƶ�����
	 * �ƶ����� = max(shift(good suffix) + shift(bad char))
	 */
	
	//aplhable_size ��ĸ��Ĵ�С���ֵ��;(A-Z)
	private static int[]  preBMBadChar(String pattern,int alphable_size)
	{
		int pattern_length = pattern.length();
		
		int i ;
		
		int[] BMBadChar = new int[alphable_size];
		
		for( i = 0 ; i < alphable_size ; i++)
		{  //1.���ַ�û������ģʽ���У��ƶ�����Ϊģʽ�����ȣ�
			BMBadChar[i] = pattern_length;
		}
		
		for(i = 0 ; i < pattern_length ; i++)
		{
			//BMBadChar���������Ƕ�Ӧpattern�����ַ��ĺ��������һ��Ԫ��֮��ľ���
			//2.���ַ�������ģʽ����,ģʽ�������ұߵĺͻ��ַ���ȵ��ַ������һ������Ԫ��֮��ľ��룻
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
		
		//1. ���Ӵ����ںú�׺��Ҳû��ǰ׺���ںú�׺�ĺ�׺���ƶ�pattern_length
		
		for(i = 0 ; i < pattern_length ; i++ )
		{
			goodSuffixed[i] = pattern_length;
		}
		//2.���Ӵ����ںúú�׺���ƶ�����patternlength - ǰ׺���ںú�׺�ĺ�׺�ĳ��ȣ�
		//j = 0;
		
		for(i = pattern_length -1 ;  i >= 0 ; --i)
		{
			if(suffixes[i] == i+1) // ǰ׺������ ������0��ʼ��
			{
				for(int j =0 ; j < pattern_length - 1 - i ; ++j)
					
				  if(goodSuffixed[j] == pattern_length)
					       
						    goodSuffixed[j] = pattern_length -1 -i;
			}
			
		}
 		// 3.�Ӵ����ںú�׺��    ������������֯��ϵ����һ������һ����% 
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
