package com.sunchao.topk;

import java.util.BitSet;

public class BloomFilter {

	/*海量数据（字符串）存在问题，
	 * 
	 * hashset+md5(SHA1)单向哈希，md5数据量为128Bit，sha1数据量为160Bit;
	 * 
	 * bitmap+hash;
	 * 
	 * trie(内存大);
	 * 
	 * 类似于bitMap来检测海量数据存在问题，但是Bitmap在才用单向哈希到对应的Bit上时，
	 * 哈希冲突在海量数据中存在较严重，将哈希冲突降到1%时，需要是所有数据的100倍；
	 * 
	 * bloomfiter思想一个bitmap 多个hash函数，将多个函数产生值对应的位置1；
	 * 
	 * 哈希函数个数k、位数组大小m、加入的字符串数量n的关系，当 k = ln(2)* m/n 时出错的概率是最小的
	 */
	
	/*  BitSet初始分配2^24个bit  */   
	private static final int  DEFAULT_SIZE = 1 << 24;
	
	/* 不同hash 函数的种子，一般取质数      */
	private static final int[] hashseed ={23, 29, 41, 53, 31, 37, 61 };
	
	private BitSet bits = new BitSet(DEFAULT_SIZE);
	
	/* 焊锡函数对象   */
	
	private  HashFunction[] hashfun = new HashFunction[DEFAULT_SIZE];        
	
	public BloomFilter()
	{
		for(int i = 0 ; i < hashseed.length ; i++)
		{
			hashfun[i] = new HashFunction(DEFAULT_SIZE,hashseed[i]);
		}
	}
	
	public void add(String value)
	{
		
		for(HashFunction fun : hashfun)
		{
			bits.set(fun.hash(value), true);
		}
	}
	
	public boolean contains(final String value)
	{
		
		if(null == value)  return false;
		
		boolean flag = true;
		
		for(HashFunction fun : hashfun)
		{
			 flag = flag && bits.get(fun.hash(value));
			 
			 if(flag == false)  break;
		}
		      
		return flag;
	}
	public static class HashFunction{
		
		int capacity;
		
		int seed;
		
		public HashFunction(int capacity,int seed)
		{
			this.capacity=capacity;
			
			this.seed = seed;
		}
		
		public int hash(final String val){
			
			int result = 0 ;
			int len = val.length();
			
			for(int i = 0 ; i < len ; i++)
			{
				result = seed * result + val.charAt(i);
			}
			return (capacity - 1) & result;
		}
		
	}
}
