package com.sunchao.maxcommondivisor_mincommonmultiple;

public class MaxCommonDivisor_MinCommMultiple {
	
	// recursive
	public static int maxCommonDivisor(int x , int y)
	{
		if(y == 0)  return x ;
		if(x < y)
		{
			int tmp = x;
			x = y ;
			y = tmp;
		}
		
		if( x % y == 0) return y;
		
		return maxCommonDivisor(y , x % y);
		
	}
	// iterator
	public static int maxCommonDivisor1(int x ,int y)
	{
		
		if(x < y)
		{
			int tmp = x ;
			x = y;
			y =tmp;
		}
		
		while(x % y != 0)
		{
			int tmp = x % y ;
			
			x = y;
			
			y = tmp ;
			
		}
		
		return y;
	}
  
	public static int minCommonMultiple(int x , int y)// x = maxCommonDivisor * x',  y = maxCommonDivisor * y'
	{ // x and y of minCommonMultiple equals x' * y' * maxCommonDivisor;
	      return x * y / maxCommonDivisor(x , y);	
	}
	
	public static void main(String args[])
	{
		
		int x = 100;
		
		int y = 45;
		
		int mcd = maxCommonDivisor(x ,y);
		
		int mcm = minCommonMultiple(x, y);
		
		System.out.println(mcd);
		
		System.out.println(mcm);
	}
}
