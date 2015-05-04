package com.sunchao.topk;

import java.util.BitSet;

public class BloomFilter {

	/*�������ݣ��ַ������������⣬
	 * 
	 * hashset+md5(SHA1)�����ϣ��md5������Ϊ128Bit��sha1������Ϊ160Bit;
	 * 
	 * bitmap+hash;
	 * 
	 * trie(�ڴ��);
	 * 
	 * ������bitMap����⺣�����ݴ������⣬����Bitmap�ڲ��õ����ϣ����Ӧ��Bit��ʱ��
	 * ��ϣ��ͻ�ں��������д��ڽ����أ�����ϣ��ͻ����1%ʱ����Ҫ���������ݵ�100����
	 * 
	 * bloomfiter˼��һ��bitmap ���hash�������������������ֵ��Ӧ��λ��1��
	 * 
	 * ��ϣ��������k��λ�����Сm��������ַ�������n�Ĺ�ϵ���� k = ln(2)* m/n ʱ����ĸ�������С��
	 */
	
	/*  BitSet��ʼ����2^24��bit  */   
	private static final int  DEFAULT_SIZE = 1 << 24;
	
	/* ��ͬhash ���������ӣ�һ��ȡ����      */
	private static final int[] hashseed ={23, 29, 41, 53, 31, 37, 61 };
	
	private BitSet bits = new BitSet(DEFAULT_SIZE);
	
	/* ������������   */
	
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
