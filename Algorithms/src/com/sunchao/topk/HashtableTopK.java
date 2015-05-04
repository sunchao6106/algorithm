package com.sunchao.topk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class HashtableTopK {
	
	private static MinHeap heap = null;
	
	private static final double A = 0.6180339887;
	
	private static final int M = 16384  ;   //m=2^14
	
	private static final int HASHTABLESIZE = 16384;
	
	private static final int HEAPSIZE = 10;
	
	private static HashNode[]  hash_table = new HashNode[HASHTABLESIZE];
	
	
	private static void buildHeap(){
		
		HashNode[] array = new HashNode[HEAPSIZE];
		
		 for (int i = 0; i < HEAPSIZE; ++i)  
		    {  
		        HashNode  node = new HashNode(i);
		        node.count =-i;
		        array[i] = node;
		    }  
		 
		 heap = new MinHeap(array);
	}
	/**  
	* hash函数采用乘法散列法 
	* h(k)=int(m*(A*k mod 1)) 
	*/  
	static int hash(int key)    
	{    
	    double result = A * key;  
	    return (int)(M * (result - (int)result));    
	} 
	
	public static  void insert(int data)  
	{  
	    int index = hash(data);  
	    HashNode pnode = hash_table[index];  
	    while (null != pnode)  
	    {   // 以存在data，则count++  
	        if (pnode.data == data)  
	        {  
	            pnode.count += 1;  
	            return;  
	        }  
	        pnode = pnode.next;  
	    }  
	      
	    // 建立一个新的节点，在表头插入  
	    pnode = new HashNode(data);  
	    pnode.next = hash_table[index];  
	    hash_table[index] = pnode;  
	}  
	  
	/**  
	* destroy_node释放创建节点产生的所有内存 
	*/  
	public static void destroyNode()  
	{  
	    HashNode p = null;  
	    HashNode tmp = null;  
	    for (int i = 0; i < HASHTABLESIZE; ++i)  
	    {  
	        p = hash_table[i];  
	        while (null != p)  
	        {  
	            tmp = p;  
	            p = p.next;  
	            tmp = null;  
	        }  
	    }  
	}  
	  
	
	  
	/**  
	* traverse_hashtale函数遍历整个hashtable，更新最小堆 
	*/  
	public static  void traverseHashtale()  
	{  
	    HashNode p = null;  
	    for (int i = 0; i < HASHTABLESIZE; ++i)  
	    {  
	        p = hash_table[i];  
	        while (null != p)  
	        {   // 如果当前节点的数量大于最小堆的最小值，则更新堆  
	            if (p.count > heap.getRoot().count)  
	            {  
	                heap.setRoot(p);  
	              //  heap[1].data = p->data;  
	              //  min_heapify(heap, HEAPSIZE, 1);  
	            }  
	            p = p.next;  
	        }  
	    }  
	}  
	  

	 static class HashNode{
		 
		 int data;
		 
		 int count;
		 
		 HashNode next;
		 
		 HashNode(int data){
			 
			 this.data = data;
			 
			 this.count = 1;
			 
		 }
		public  String toString(){
			
			return data+"."+count;
		}
	 }
	 
	 public static void main(String args[]) throws IOException{
		 
		buildHeap();
		 
		 /*FileWriter fileWriter = new FileWriter(new File("C:/java/hello"));
		 BufferedWriter writer = new BufferedWriter(fileWriter);
		 StringBuffer  buffer = new StringBuffer();
		 int result =0;
		 Random  random = new Random(37);
		 for(int i =0 ; i< 10000; i++){
			 
		      result = random.nextInt(500);
		      
		      buffer.append(result +"\r\n");
		 }
		 
		 writer.write(buffer.toString());
		 writer.close();
		 fileWriter.close();*/
		 
		 FileReader  fileReader = new FileReader(new File("C:/java/hello.txt"));
		 
		 BufferedReader  reader = new BufferedReader(fileReader);
		 
		// reader.re
		 String s;
		 while((s= reader.readLine()) != null){
			 
			  String s1= s.trim();
			  
				  
				  int abc =Integer.parseInt(s1);
				  insert(abc);
			  
		 }
		 
		 System.out.println(Arrays.toString(hash_table));
		   fileReader.close();
		   reader.close();
		   traverseHashtale();
		   
		   HashNode[] data =heap.getData();
		   
		   for(int i =0 ; i< 10 ;i++){
		   System.out.println(data[i].count);
		   }
	 }
  	 
}
