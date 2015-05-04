package com.sunchao.maxcommondivisor_mincommonmultiple;

import java.util.Arrays;

public class PrimeFactor {
	//O(n);
	public static boolean  isPrime(int num)
    {
	    
		if(num < 2)  return false;
		
		for(int i = 2 ; i < num ; i++)
		{
			
			if(num % i == 0) return false;
		}
		
		return true;
	}
    //ȥ��ż��,O(n/2);
	public static boolean isPrime1(int num)
	{
		
		if(num < 2)  return false;
		
		if(num ==  2)  return true;
		
		if(num % 2 == 0)  return false;
		
		for(int i = 3 ; i < num ; i+=2)
		{
			
			if(num % i == 0)  return false;
		}
		
		return true;
	}
	/* ����: ���n��������, ��n������1< d<=sqrt(n)��һ������d.
	 * 
	 *///O((n-Math.sqrt(n)) / 2)
	public static boolean isPrime2(int num)
	{
		if(num < 2)  return false;
		if(num == 2)  return true;
		if(num % 2 == 0)  return false;
		
		for(int i = 3 ; i*i <= num ; i+=2)
		{
			if(num % i == 0)  return false;
		}
		
		return true;
	}
	/* 
     * �����������num���зֽ���������Ӧ���ҵ�һ����С������k��Ȼ������������ɣ� 
     * (1)����������ǡ����num����˵���ֽ��������Ĺ����Ѿ���������ӡ�����ɡ� 
     * (2)���n>=k����n�ܱ�k��������Ӧ��ӡ��k��ֵ������num����k����,��Ϊ�µ���������num,�ظ�ִ�е�һ����    
     * (3)���num���ܱ�k����������k+1��Ϊk��ֵ,�ظ�ִ�е�һ����   
     */  
	public static void primeFactor(int num)
	{
		
		if(num < 2)
		{
		   System.out.println("^#^,please enter a num  that >= 2");
		   
		   return;
		}
		
		int primeNumber =2;
		
		System.out.print("^#^ the num :" + num + "=");
		
		while(primeNumber <= num)
		{
			
			if(primeNumber == num)
			{
				System.out.print(num);
			    break;
			}else if(num % primeNumber == 0){
				
				System.out.print(primeNumber +"*");
				
				num = num / primeNumber;
			}else
				primeNumber++;
		}
	}
	// ���ö�ÿ�������ı����ض�����������ɸѡ 
	public static void getPrime(int m){
		
		int[] flag = new int[m/32 +1];
		int count =0;
		int[] primes = new int[m];
		
		for(int i = 2 ; i <= m ;i++)
		{
			if(((flag[i/32] >> (i % 32)) & 1) == 0)
			{
				primes[count++] = i;
			}
			
			for(int j = i ; j <= m ; j += i)
			{
				flag[j/32] |= (1 << (j % 32));
			}
			
		}
		
		for(int i = 0 ; i < count ; i++)
		{
			System.out.print(primes[i] + " ");
			
		}
	}
	// ������ÿ����������һ����С��������ɸѡ 
	public static void getPrime1(int m)
	{
		int[] flag = new int[m/32+1];
		
		int count = 0;
		
		int[] primes = new int[m];
		
		int i, j ;
		
		for( i = 2 ; i <= m ; i++)
		{
			if(((flag[i/32] >> (i % 32)) & 1) == 0 )
			{
				primes[count++] = i;
			}
			
			for(j = 0 ; (j < count) && (i * primes[j] <= m) ; j++)
			{
				flag[(i * primes[j]) / 32] |= (1 << ((i * primes[j]) % 32));
				
				if(( i % primes[j]) == 0)
					
					break;
				
			}
		}
		
		for(i = 0 ; i < count ; i++)
		{
			System.out.print(primes[i] +" ");
		}
		
	}
	
	
	/*�����������£�

	*�Ƚ�1�ڵ�(��Ϊ1��������)��
	*��2ȥ��������ĸ����������ܱ�2���������ڵ�������2�ı����ڵ���
	*��3ȥ��������ĸ�������3�ı����ڵ���
	*�ֱ���4��5��������Ϊ����ȥ����Щ���Ժ�ĸ������������һֱ���е��ڳ������������ȫ���ڵ�Ϊֹ��������1��50��������Ҫһֱ���е�����Ϊ47Ϊֹ����ʵ�ϣ����Լ򻯣������Ҫ��1��n��Χ��������ֻ����е�����Ϊn^2(����n)��ȡ���������ɡ������1��50��ֻ����е���50^2��Ϊ�������ɡ���
	*�����㷨�ɱ�ʾΪ��
	*��ȥ1;
	*�øղű���ȥ��������һ����pȥ��p�����������p�ı����ڵ�;
	*���p�Ƿ�С��n^2���������֣����n=1000, ����p<31?��������ǣ��򷵻�(2)����ִ�У�����ͽ���;
	*ֽ��ʣ�µ�������������
	*/
	public static void getPrime2(int m)
	{
		int i,j;
		
		int[] primes = new int[m+1];
		
		for( i = 1 ; i<= m ; i++)
		{
			primes[i] =i;
		}
		
		for(i = 2 ; i < (int)Math.sqrt(m) ; i++)
		{
		       for(j = i + 1 ; j <= m ; j++)
		       {
		    	   if(primes[i] != 0 && primes[j] != 0)
		    	   {
		    		   if(primes[j] % primes[i] == 0)
		    			   
		    			   primes[j] = 0 ;
		    	   }
		       }
		}
		
		int n = 0;
		for(i = 2 ; i <= m ; i++)
		{
			if(primes[i] != 0)
			{
				System.out.printf("%-5d", primes[i]);
				n++;
			}
			if(n == 10)
			{
				System.out.println();
				n = 0; 
			}
		}
	}
	
	
	public static void main(String args[]){
		
		getPrime2(300);
		//int num = -1;
		//primeFactor(num);
	}
}
