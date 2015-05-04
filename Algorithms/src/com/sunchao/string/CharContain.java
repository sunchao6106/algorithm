package com.sunchao.string;

public class CharContain {

	
	public static boolean stringContain(String longString,String shortString){//O(n*m)
		
		if(shortString.length() == 0) return true;
		
		if(shortString.length() > longString.length()) return false;
		
		int sLength = shortString.length();
		
		int lLength=longString.length();
		
		for(int i = 0 ; i < sLength ; i++){
			
			char sChar = shortString.charAt(i);
			int j ;
			for(j = 0 ; j < lLength ; j++){
				
				if(longString.charAt(j) == sChar)  break;
						
			}
			if(j == lLength) return false;
		}
		return true;
	}
	public static boolean stringContain2(String ls,String ss){
		
		 int posLong = 0,posShort =0;
		
		 ls = countSort(ls.toCharArray());
		 
		 ss = countSort(ss.toCharArray());
		 
		 while(posLong < ls.length() && posShort < ss.length())
		 {
			 
			 while(ls.charAt(posLong) < ss.charAt(posShort) && posLong < ls.length()-1)
			 {
				   posLong++;
			 }
			 
			 while(posShort < ss.length()-1 &&ss.charAt(posShort) == ss.charAt(posShort+1))  
			 {
				 posShort++;
			 }
			 
			 if(ls.charAt(posLong) != ss.charAt(posShort))
			 {
				 break;
			 }
			 posLong++;
			 posShort++;
		 }
		 
		if(posShort == ss.length()) return true;
		else 
			return false;
		
	}
	
	private static String countSort(char[] charArray){
		
		int helper[] = new int[26];// charArray the max number+1;
		
		char[] tmp  = new char[charArray.length];
		
		for(int i = 0 ; i < charArray.length ; i++)
		{
		
			int index = charArray[i]-'A';
			
			helper[index]++;
		}
		
		for(int j = 1 ; j < 26 ; j++ )
		{
			
			helper[j] += helper[j-1];
			
		}
		
		for(int k = charArray.length-1 ; k >= 0 ; k--)
		{
			
			int index = charArray[k] - 'A';
			
			int pos = helper[index] - 1 ;
			
			tmp[pos] = charArray[k];
			
			helper[index]--;
			
		}
		return new String(tmp);
	}
	public static boolean stringContain1(String longString, String lowString){//O(nlgn)+O(mlgm)+O(n+m);
		
		char[]  longA = longString.toCharArray();
		char[]  lowA = lowString.toCharArray();
		quickSort(longA);
		quickSort(lowA);
		int llength  = longA.length;
		int slength  = lowA.length;
		int posOne=0,posTwo=0;
		
		while(posTwo < slength && posOne < llength)
		{
			while(posOne < llength-1 &&longA[posOne] < lowA[posTwo] )
			{
				posOne++;
			}
			if(longA[posOne] != lowA[posTwo])
				break;
			
			posTwo++;
		}
		
 		if(posTwo == slength) return true;
 		
 		else
 			return false;
	}
	
	public static void quickSort(char[] charArray){
		
		//char[] charArray = s.toCharArray();
		quickSort(charArray,0,charArray.length-1);
	}
	private static void quickSort(char[] charArray,int lo, int hi){
		
		if(lo < hi)
		{
			int k = partition(charArray,lo,hi);
			quickSort(charArray,lo,k-1);
			quickSort(charArray,k+1,hi);
		}
	}
	
	private static final int partition(char[] charArray,int lo ,int hi){
		
		int i = lo-1,j;
		
		int key = charArray[hi];
		
		for(j = lo ; j < hi ; j++)
		{
		     
			if(charArray[j] <= key)
			{
			       i++;
			       
			       swap(charArray,i,j);
			}
			
		}
		swap(charArray,i+1,hi);
		return i+1;
	}
	
	private static final void swap(char[] charArray , int x, int y){
		
		char tmp = charArray[x];
		
		charArray[x] = charArray[y];
		
		charArray[y] = tmp;
		
	}
	
	
	
	public static void main(String args[]){
		
		String s1= "ABCDEFGHLMNOPQRS";
		String s2= "BBBBBBBBBDCGSRQPOAAAAAAAAAAAAAAA";
		String s3 = countSort(s2.toCharArray());
		System.out.println(s3);
		boolean flag = stringContain2(s1,s2);
		System.out.println(flag);
	}
			
}
