package com.sunchao.array;

public class MaxSubSqueueceSum {
	
	
	public static int maxSubSum1(int[] array){//violent  O(n^3);
		int size = array.length;
		int maxSum =Integer.MIN_VALUE;
		
		for(int i =0 ; i< array.length; i++){
			
			for(int j =i ;j< array.length; j++){
				
				int subMax = 0;
				
				for(int k = i ; k <= j ;k++){
					
					subMax+=array[k];
				}
				
				if(subMax > maxSum)  maxSum = subMax;
			}
		}
		
		return maxSum;
	}
	
	public static int maxSubSum3(int[] array){//divide and conquer
		
		return maxSubSum3(array,0,array.length-1);
		
	}
	
	private static int maxSubSum3(int[] array,int left,int right){
		
		if(left == right){
			
			return array[left];
			
			/*if(array[left] > 0)  return array[left];
			
			else
				
				return 0;*/
		}
		
		int center = (left+right)/2;
		
		int maxLeftSum = maxSubSum3(array,left,center);
		
		int maxRightSum = maxSubSum3(array,center+1,right);
		
		int maxLeftBorderSum = Integer.MIN_VALUE,leftBorderSum =0;
		
		for(int i =center ; i >= left ;i--){
			
			leftBorderSum += array[i];
			
			if(leftBorderSum > maxLeftBorderSum) maxLeftBorderSum =  leftBorderSum;
		}
		
		int maxRightBorderSum =  Integer.MIN_VALUE,rightBorderSum  = 0;
		
		for(int j = center+1 ; j <= right ; j++){
			
			rightBorderSum += array[j];
			
			if(rightBorderSum > maxRightBorderSum) maxRightBorderSum = rightBorderSum;
			
		}
		
		return max3(maxLeftSum,maxRightSum,maxLeftBorderSum+maxRightBorderSum);
	}
	
	private static  int max3(int a, int b, int c){
		
		if(a < b)  a=b;
		
		if(a > c)  return  a;
		
		else 
			return c;
		
	}
	
	public static int maxSubSum4(int[] array){
		
		int size = array.length;
		
        int maxSum = Integer.MIN_VALUE,subSum =0 ;
        
		for(int i =0 ; i < size ;i++){
			
			if(subSum < 0)  subSum = array[i];
			
			else 
				  subSum += array[i];
			
			if(subSum > maxSum)  maxSum = subSum;
			
		}
		
		return maxSum;
	}
	public static int maxSubSum2(int[] array){//violent O(n^2);
		
		int size = array.length;
		
		int maxSum = Integer.MIN_VALUE;
		
		for(int i =0 ; i < size ;i++){
			
			int subSum =0;
			
			for(int j = i ; j < size ; j++){
				
				subSum+=array[j];
				
				if(subSum > maxSum)  maxSum = subSum;
			}
		}
		return maxSum;
	}
	
	public  static void main(String args[]){
		
		int[] array ={-2,-1,3,4,-3,-4};
		//int maxSum = maxSubSum1(array);
		int maxSum=maxSubSum3(array);
		System.out.println(maxSum);
	}

}
