package com.sunchao.array;

import java.util.Arrays;

public class KSmallerSelect {
	
	private static void swap(int[] array,int x , int y)
	{
	     
		int tmp = array[x];
		array[x] =array[y];
		array[y]=tmp;
		
		
	}
	
	private static int partition(int[] array,int left,int right)
	
	{
		
		int pos = left -1;
		
		int key = array[right];
		for(int  j = left ; j < right ; j++){
			
			if(array[j] < key){
				
				swap(array,++pos,j);
			}
		}
		swap(array,++pos,right);
		
		return pos;
	}
	
	
	public static int randomPartition(int[] array,int left,int right)
	{
		
		int index = myRandom(left, right);
		
		swap(array,index,right);
		
		return partition(array, left, right);
		
	}
	
	
	public static int randomSelect(int[] array,int left,int right,int k)
	{
		if(k < 1 || k > (right - left + 1)) return -1;
		
		int pos = randomPartition(array, left, right);
		
		int m = pos -left + 1;// the left to  the pos  size;
		
		if(m == k)  
			
	        return array[pos]; 
		
	    else if (m > k)    
	    	
	        return randomSelect(array, left, pos - 1, k);  
		
	    else   
	        return randomSelect(array, pos + 1, right, k-m);  
		
	}
	private static int myRandom(int lo , int hi)
	{
		
		int size = hi - lo +1;
		
		return  lo +(int)(Math.random() % size);
		
		
	}
	
	public static void main(String args[]){
		
		
		int array[] = {7, 8, 9, 54, 6, 4, 2, 1, 12, 33};  
	   int  value = randomSelect(array, 0, array.length-1, 8) ;
	  //  return 0;  
	    System.out.println(value);
	    System.out.print(Arrays.toString(array));
	}

}
