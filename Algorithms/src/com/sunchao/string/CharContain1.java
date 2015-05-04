package com.sunchao.string;

public class CharContain1 {
	
	public static boolean charContain1(String ls,String ss){//O(n+m)  bitmap;
		
		int flag =0; //a-z|A-Z 26bit int   a-zA-Z 52 long
		
		char[] longArray = ls.toCharArray();
		char[] shortArray = ss.toCharArray();
		
		for(char c : shortArray)
		{
			
			int shift = c- 'A';
			flag |= (1 << shift);
		}
		
		for(char c : longArray)
		{
		    int shift = c - 'A';
		    
		    flag &= (~(1 << shift));
		}
		
		if(flag == 0)  return true;
		else
			return false;
		
	}
	public static boolean charContain(String ls,String ss){//O(n+m)

		int[] hash = new int[26];
		
		int nums =0;
		
		for(int i = 0 ; i < ss.length() ; i++)
		{
			
			int index  = ss.charAt(i) - 'A';
			
			if(hash[index] == 0)
			{
		         hash[index] = 1;
		         
		         nums++;
			}
		} 
		
		for(int j = 0 ; j < ls.length() ; j++){
			
			int index = ls.charAt(j) - 'A';
			
			if(hash[index] == 1)
			{
				hash[index] = 0 ;
				
				nums--;
				
				if(nums == 0) break;
			}
			
		}
		
		if(nums == 0)  return  true;
		else
			return false;
		
		
	}
	
	public static void main(String args[]){
		
		String str1="ABCDEFGHLMNOPQRS";  
	    String str2="DCGSRQPOM"; 
	    
	    boolean flag = charContain1(str1,str2);
	    
	    System.out.println(flag);
		
	}

}
