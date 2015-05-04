package com.sunchao.sort;

import java.util.Arrays;

public class DoubleQuickSort {
	
	private static final void swap(int[] array,int x,int y){
		
		int temp = array[x];
		
		array[x] = array[y];
		
		array[y] = temp;
		
	}
	
	private static final int partition(int[] array,int lo,int hi){
		
		int key = array[lo];
		
		int i = lo,j = hi;
		
		while(i < j){
			
			while(array[j] >= key && i < j) j--;
			
			array[i] = array[j];
			
			while(array[i] <= key && i < j) i++;
			
			array[j] = array[i];
			
			
		}
		
		array[i] = key;
		
		return i;
	}
	
	private static void quickSort(int[] array,int lo,int hi){
		
		if(lo < hi){
			
			int k = partition(array,lo,hi);
			
			quickSort(array,lo,k-1);
			quickSort(array,k+1,hi);
		}
	}
	
   public static void quickSort(int[] array){
	   
	   quickSort(array,0,array.length-1);
   }
   
   public static void main(String args[]){
	   
	  int[] array= { 3  , 8  , 7  , 1  , 2  , 5  , 6   ,4};
	  
	  quickSort(array);
	  System.out.println(Arrays.toString(array));
   }
}
