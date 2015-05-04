package com.sunchao.array;

import java.util.Arrays;

public class KthSmallSelect {
 
	  private static int myRandom(int left , int right){
		  
		  int size = right - left +1;
		  
		  return left + (int)(Math.random() % size);
	  }
	  
	  
	  private static void swap(int[] array , int x ,int y)
	  {
		  
		  int tmp = array[x] ;
		  
		  array[x] = array[y];
		  
		  array[y] = tmp;
	  }
	  
	  public static int partition(int[] array, int left , int right)
	  {
		  int pos = left - 1 ;
		  
		  int key = array[right];
		  
		  for(int i = left ; i < right ; i++)
		  {
			  
			  if(array[i] <= key)
			  {
				  swap(array,++pos,i);
			  }
		  }
		  
		  swap(array,++pos,right);
		  
		  return pos;
	  }
	  
	  public static int randomPartition(int[] array, int left ,int right){
		  
		  int random = myRandom(left, right);
		  
		  swap(array,random,right);
		  
		  return partition(array,left,right);
	  }
	  
	  public static int randomSelect(int[] array,int left , int right ,int k){
		  
		  if( k-1 > right || k-1 < left)  return -1;
		  
		  int pos = randomPartition(array,left,right);
		  
		  int m = pos - left + 1 ;
		  
		  
		  if(m == k) return array[m];
		  
		  else if(k  < m) return randomSelect(array,left,pos-1,k);
		  
		  else
			  return randomSelect(array,pos+1,right,k-m);
	  }
	  
	  public static void main(String args[]){
			
			
			int array[] = {7, 8, 9, 54, 6, 4, 2, 1, 12, 33};  
		   int  value = randomSelect(array, 0, array.length-1, 3) ;
		  //  return 0;  
		    System.out.println(value);
		    System.out.print(Arrays.toString(array));
		}
}
