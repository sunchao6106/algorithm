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
    //去掉偶数,O(n/2);
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
	/* 定理: 如果n不是素数, 则n有满足1< d<=sqrt(n)的一个因子d.
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
     * 程序分析：对num进行分解质因数，应先找到一个最小的质数k，然后按下述步骤完成： 
     * (1)如果这个质数恰等于num，则说明分解质因数的过程已经结束，打印出即可。 
     * (2)如果n>=k，但n能被k整除，则应打印出k的值，并用num除以k的商,作为新的正整数你num,重复执行第一步。    
     * (3)如果num不能被k整除，则用k+1作为k的值,重复执行第一步。   
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
	// 利用对每个素数的倍数必定不是素数来筛选 
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
	// 利用了每个合数必有一个最小素因子来筛选 
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
	
	
	/*具体做法如下：

	*先将1挖掉(因为1不是素数)。
	*用2去除它后面的各个数，把能被2整除的数挖掉，即把2的倍数挖掉。
	*用3去除它后面的各数，把3的倍数挖掉。
	*分别用4、5…各数作为除数去除这些数以后的各数。这个过程一直进行到在除数后面的数已全被挖掉为止。例如找1～50的素数，要一直进行到除数为47为止（事实上，可以简化，如果需要找1～n范围内素数表，只需进行到除数为n^2(根号n)，取其整数即可。例如对1～50，只需进行到将50^2作为除数即可。）
	*如上算法可表示为：
	*挖去1;
	*用刚才被挖去的数的下一个数p去除p后面各数，把p的倍数挖掉;
	*检查p是否小于n^2的整数部分（如果n=1000, 则检查p<31?），如果是，则返回(2)继续执行，否则就结束;
	*纸上剩下的数就是素数。
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
