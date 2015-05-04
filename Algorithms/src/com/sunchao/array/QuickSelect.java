package com.sunchao.array;

import java.util.Arrays;

public class QuickSelect {

	private static final int CUTOFF = 10;
	private static void swap(int[] array,int x,int y){
		
		/*array[x] = array[x] ^ array[y];
		
		array[y]  = array[x] ^ array[y];
		
		array[x] = array[x] ^ array[y];*/
		int temp = array[x];
		array[x] = array[y];
		array[y] =temp;
	}
	
	private static int median3(int[]  array, int left,int right){
		
		//if(left == right)  return array[left];
		
		int center = (left + right) /2;
		
		if(array[center] < array[left]) swap(array,left,center);
		
		if(array[right] < array[left])  swap(array,left,right);
		
		if(array[right]  < array[center]) swap(array,center,right);
		
		swap(array,center,right-1);
		
		return array[right-1];
		
	}
	public static int  quickSelect(int[] array,int k ,int left,int right){
		
		int i,j,pivot;
		
		/*if(left + CUTOFF <= right)
		{*/
			
			pivot = median3(array,left,right);
			
			i = left;
			j = right -1;
			
			for(;;){
				
				while(array[++i] < pivot){}
				
				while(array[--j] > pivot){}
				
				if(i < j) swap(array,i,j);
				
				else
					break;
			}
			
			swap(array,i,right-1);
			
			//int m =i - left +1;
			
			if(i == k) return array[i];
			
			else if(i > k) return quickSelect(array,k,left,i-1);
			else  
				return quickSelect(array,k,i+1,right);
		}
	/*	else{
			
			insertSort(array,left,right);
		}*/
		
		//
	//}
	
	private static void insertSort(int[] array,int left,int right){
		
		 int j;
		 
	     for(int i = left+1; i < right ; i++){
	    	 
	    	 int tmp = array[i];
	    	 
	    	 for(j = i ; j > 0 && array[j] < array[j-1] ; j-- ){
	    		 
	    		   array[j]=array[j-1];
	    	 }
	    	 array[j]=tmp;
	     }
		
	}
	
	public static void main(String args[]){
		
		int[]  array ={0,45,78,55,47,4,1,2,7,8,96,36,45};
		
		int k = quickSelect(array,2,0,array.length-1);
		System.out.println(k);
		System.out.println(Arrays.toString(array));
		
		//System.out.println(array[6-1]);
		
	}
}
