package com.sunchao.topk;

import com.sunchao.topk.HashtableTopK.HashNode;



public class MinHeap {
    
	  private static HashNode[] data;
	  
	  
	  public MinHeap(HashNode[] data){
		  
		  this.data = data;
		  
		  buildMinHeap();
		  
	  }
	  
	  private static final void swap(int x,int y){
		  
		  HashNode temp = data[x];
		  data[x] = data[y];
		  data[y] =temp;
	  }
	  
    
	  
	  public static void heapify(int index){
		  
		  heapify(index,data.length);
		  
	  }
	  
	  private static void heapify(int index ,int length){
		  
		  
		  int next = index;
		  
		  int left = leftChild(index);
		  
		  int right = rightChild(index);
		  
		  if(left < length && data[left].count < data[next].count){
			  
			    next = left;
		  }
		  
		  if(right < length && data[right].count < data[next].count){
			  
			     next = right;
		  }
		  
		  if(next == index)  return ;
		  
		  swap(index,next);
		  
		  heapify(next,length);
		  
	  }
	  private static int parent(int index){
		  
		  return (index - 1) >> 1;
	  }
	  
	  private static int leftChild(int index){
		  
		  return ((index+1) << 1)-1;
	  }
	  
	  private static int rightChild(int index){
		  
		  return (index+1) <<1;
	  }


	 public  static void buildMinHeap() {
		// TODO Auto-generated method stub
	     int length = data.length;
	     
	     for(int i = parent(length-1) ; i >= 0 ;i--){
	    	 
	    	  heapify(i);
	    	 
	     }
	}
	 
	public HashNode getRoot(){
		
		return data[0];
		
	}
	
	public HashNode setRoot(HashNode root){
		
		HashNode oldValue = data[0];
		
		data[0] = root;
		
		heapify(0);
		
		return oldValue;
	}
	
	public HashNode[] getData(){
		
		return data;
	}
} 
