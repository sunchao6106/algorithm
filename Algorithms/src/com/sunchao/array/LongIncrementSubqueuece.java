package com.sunchao.array;

import java.util.Arrays;

public class LongIncrementSubqueuece {
	
	
	public static int lis1(int[] array){
		
		int size  = array.length;
		
		int[] B = new int[size+1];
		
		B[0] = Integer.MIN_VALUE;
		
		B[1] = array[0];
		
		int len = 1;
		
		for(int i = 1 ; i < size ; i++){
			
			int head =0,tail=len,middle;
			
			if(array[i] > )
			
			while(head <= tail){
				
				middle =(head+tail)/2;
				
				if(B[middle] < array[i]) head = middle+1;
				
				else  tail = middle-1;
				
			}
			B[head] = array[i];
			if(head > len)  len = head;
			
		}
		return len;
	}
 
	
	public static int lis(int[] array){//O(n^2)
		
		int length = array.length;
		
		if(length == 1)  return 1;
		
		int[] liss = new int[length];
		
		int[] pre = new int[length];
		
		int[] result = new int[length];
		
		int max,k,i,j;
		for( i = 0; i < length; ++i)  
	    {  
	        liss[i] = 1;  
	        pre[i] = i;  
	    }  
	  
	    for(i = 1, max = 1, k = 0; i < length; ++i)  
	    {  
	        //找到以array[i]为最末元素的最长递增子序列  
	        for(j = 0; j < i; ++j)  
	        {  
	            //如果要求非递减子序列只需将array[j] < array[i]改成<=，  
	            //如果要求递减子序列只需改为>  
	            if(array[j] < array[i] && liss[j] + 1> liss[i])  
	            {  
	                liss[i] = liss[j] + 1;  
	                pre[i] = j;  
	  
	                //得到当前最长递增子序列的长度，以及该子序列的最末元素的位置  
	                if(max < liss[i])  
	                {  
	                    max = liss[i];  
	                    k = i;  
	                }  
	            }  
	        }  
	    }  
	  
	    //输出序列  
	    i = max - 1;  
	  
	    while(pre[k] != k)  
	    {  
	        result[i--] = array[k];  
	        k = pre[k];  
	    }  
	  
	    result[i] = array[k]; 
	    System.out.println(Arrays.toString(result));
	  
	    return max;  
		
	}
			
	public static void main(String args[]){
		
		int array[]  = {1,3,5,6,2};
		int size = lis1(array);
		System.out.println(size);
	}
			
}
