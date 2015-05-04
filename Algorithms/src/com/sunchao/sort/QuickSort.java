package com.sunchao.sort;

import java.util.Arrays;

public class QuickSort {
	
	private static int partition(int[] array,int lo,int hi){
		
		int key =  array[hi];
		
		int i = lo - 1;
		
		for(int j = lo ; j < hi ; j++){
			
			if(array[j] < key){
				
				i = i+1;
				swap(array,i,j);
			}
		}
		swap(array,i+1,hi);
		
		return i+1;
	}
	
	private static void swap(int[] array,int x,int y){
	/*	
		array[x] = array[x] ^ array[y];
		
		array[y] = array[x] ^ array[y];
		
		array[x] = array[x] ^ array[y];*/
		
		int tmp = array[x];
		array[x] = array[y];
		array[y] =tmp;
		
	}
	
	private static void quickSort(int[] array,int lo ,int hi){
		
		//int lo = 0,hi= array.length-1;
		
		if(lo < hi){
			
			int k  = partition(array,lo,hi);
			
			quickSort(array,lo,k-1);
			quickSort(array,k+1,hi);
		}
		
	}
    public static void quickSort(int[] array){
    	
               quickSort(array,0,array.length-1);    	
    }
    
    public static void main(String args[]){
    	
    	int[] array ={4,6,81,5,7,9,0,23,43,11,35};
    	
    	quickSort(array);
    	
    	System.out.println(Arrays.toString(array));
    	
    }
}
