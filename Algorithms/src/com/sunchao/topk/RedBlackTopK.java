package com.sunchao.topk;

import com.sunchao.topk.HashtableTopK.HashNode;

public class RedBlackTopK {
	
	private static RBNode root;
	/**  
	* ����   
	* 
	*  node           right  
	*  / /    ==>     / /  
	* a  right     node  y  
	*     / /       / /      
	*     b  y     a   b     
	*/  
	
	public static RBNode rb_LeftRotate(RBNode node){
		
		RBNode right = node.right;
		
		node.right = right.left;
		
		if(right.left != null)
			
			right.left.parent = node;
		
		right.left = node;
		
		if(node.parent != null)
		{
			right.parent = node.parent;
			
			if(node == node.parent.right)
				node.parent.right=right;
			else
				node.parent.left=right;
			
		}else{
			
			root = right;
		}
		node.parent = right;
			
		return right;
		
	}
	
	/** 
	* ����   
	* 
	*      node            left  
	*       / /             / /  
	*     left y   ==>     a  node  
	*     / /                  / /  
	*    a   b                b   y   
	*/    
	
	
	public static RBNode rb_RightRotate(RBNode node){
		
		RBNode left = node.left;
		node.left = left.right;
		
		if(left.right != null)
		{
			 left.right.parent = node;
		}
		
		left.right = node;
		
		if(node.parent != null){
			
			left.parent = node.parent;
			
			if(node == node.parent.left)
			{
				node.parent.left = left;
			}else{
				
				node.parent.right=left;
			}
		}else
			
			root=left;
		
	    node.parent = left;
	    
	    return left;
	}
	
	/**  
	* �������3�ֲ������   
	* ��z��ʾ��ǰ���, p[z]��ʾ��ĸ��p[p[z]]��ʾ�游, y��ʾ����. 
	*/  
	public static RBNode  rbInsert_Rebalance(RBNode node)    
	{    
	    RBNode parent, gparent, uncle, tmp,newNode =null;  //��ĸp[z]���游p[p[z]]������y����ʱ���*tmp    
	      
	    while ((parent = node.parent) !=null && parent.color == RB_COLOR.RED)    
	    { // parent Ϊnode�ĸ�ĸ���ҵ���ĸ����ɫΪ��ʱ    
	        gparent = parent.parent;   // gparentΪ�游    
	          
	        if (parent == gparent.left)  // ���游�����Ӽ�Ϊ��ĸʱ,��ʵ����������䣬�޷Ǿ�����˳���ӡ���ĸ���游�Ĺ�ϵ��  
	        {  
	            uncle = gparent.right; // ��������ĸ������y���Ǹ�ĸ���Һ��ӡ�    
	            if (uncle != null && uncle.color == RB_COLOR.RED) // ���1��z������y�Ǻ�ɫ��    
	            {    
	                uncle.color = RB_COLOR.BLACK;   // ��������y��Ϊ��ɫ    
	                parent.color =RB_COLOR. BLACK;  // z�ĸ�ĸp[z]Ҳ��Ϊ��ɫ�����z��p[z]���Ǻ�ɫ�����⡣    
	                gparent.color = RB_COLOR.RED;      
	                node = gparent;     // ���游�����������z��ָ��z�������㣬����Ϊ��ɫ��    
	                // �������1�У�ֻ������z��Ϊ��ĸ���Һ��ӵ������    
	            }    
	            else                     // ���2��z������y�Ǻ�ɫ�ģ�    
	            {       
	                if (parent.right == node)  // ��zΪ�Һ���    
	                {    
	                    newNode = rb_LeftRotate(parent); // ����[���z���븸ĸ���]    
	                    tmp = parent;    
	                    parent = node;    
	                    node = tmp;     // parent��node ������ɫ    
	                }    
	                // ���3��z������y�Ǻ�ɫ�ģ���ʱz��Ϊ�����ӡ�    
	                // ע�⣬1�����3�����������2�仯�����ġ�    
	                // ......2��z���������Ǻ�ɫ�ģ�����������1�ˡ�    
	                parent.color = RB_COLOR.BLACK;   // z�ĸ�ĸp[z]��Ϊ��ɫ    
	                gparent.color = RB_COLOR.RED;    // ԭ�游�����Ϊ��ɫ    
	                newNode = rb_RightRotate(gparent); // ����[���z�����游���]    
	            }    
	        }     
	          
	        else     
	        {         
	            // �ⲿ�����ر�Ϊ���1�У�z��Ϊ�����������д�ġ�    
	            uncle = gparent.left;  // �游��������Ϊ�����㡣[ԭ�������ϲ���һ����]    
	            if (uncle != null && uncle.color == RB_COLOR.RED)  // ���1��z������y�Ǻ�ɫ��    
	            {    
	                uncle.color = RB_COLOR.BLACK;    
	                parent.color = RB_COLOR.BLACK;    
	                gparent.color = RB_COLOR.RED;    
	                node = gparent;           // ͬ��  
	            }    
	            else                               // ���2��z������y�Ǻ�ɫ�ģ�    
	            {    
	                if (parent.left == node)  // ��zΪ����    
	                {    
	                    newNode = rb_RightRotate(parent);  // �Խ��parent��root����    
	                    tmp = parent;    
	                    parent = node;    
	                    node = tmp;       // parent��node ������ɫ    
	                }     
	                // �������2�ı仯����Ϊ�����3.    
	                parent.color = RB_COLOR.BLACK;    
	                gparent.color = RB_COLOR.RED;    
	                newNode = rb_LeftRotate(gparent);   // �Խ��gparent��root����    
	            }    
	        }    
	    }    
	      
	    newNode.color = RB_COLOR.BLACK; // ����㣬����������������Ϊ��ɫ��    
	    return newNode;      // ���ظ���㡣    
	}  
	  
	/**  
	* ��������ҽ��   
	* rb_search_auxiliary������   
	* RBNode rb_search�������ҵ��Ľ��   
	*/  
	public static RBNode  rbSearchAuxiliary(int key, RBNode root,boolean flag)    
	{    
	    RBNode node = root;  
	    RBNode parent = null;    
	    int ret;    
	      
	    while (node != null)    
	    {    
	        parent = node;    
	        ret = node.key - key;    
	        if (0 < ret)    
	            node = node.left;    
	        else if (0 > ret)    
	            node = node.right;    
	        else    
	            return node;    
	    } 
	    if(flag){
	    	return parent;
	    }
	      
	    return null;    
	}    
	  
	/**  
	* ��������rb_search_auxiliary���ҽ��   
	*/  
	public static RBNode rbSearch(int key, RBNode root,boolean flag)    
	{    
	    return rbSearchAuxiliary(key, root,flag);    
	}    
	  
	/**  
	* ������Ĳ���   
	*/  
	public static RBNode rb_Insert(int key, int data, RBNode root)    
	{    
	    RBNode parent = null;  
	    RBNode node = null;    
	      
	    parent = null;    
	    if ((node = rbSearchAuxiliary(key, root, false)) != null)  // ����RB_SearchAuxiliary�ҵ�������ĵط�    
	    {    
	        node.data++; // �ڵ��Ѿ�����dataֵ��1  
	        return root;    
	    }    
	    parent = rbSearchAuxiliary(key, root, true);
	    node = new RBNode(key, data);  // ������    
	    node.parent = parent;        
	      
	    if (parent != null)    
	    {    
	        if (parent.key > key)    
	            parent.left = node;    
	        else    
	            parent.right = node;    
	    }    
	    else    
	    {    
	        root = node;    
	    }    
	      
	    return rbInsert_Rebalance(node);   // ������󣬵���RB_Insert_Rebalance�޸������������    
	}  
	 
	
	
	
	static class RBNode{
		
		int key;
		int data;
		RB_COLOR color;
		RBNode parent;
		RBNode left;
	    RBNode right;
		
		public RBNode(int key,int data)
		{
			this.key =key;
			this.data=data;
			color=RB_COLOR.RED;
		}	
		
	}
	
	
	static enum RB_COLOR{
		
		RED,BLACK;
	}
	
	static class  RBHeap  
	{  
	    int key;  
	    int data;  
	}  
	private static int heapSize = 10; 
	
	private static RBHeap heap[] = new RBHeap[heapSize];  
	  
	/**  
	* MAX_HEAPIFY�����Զѽ��и��£�ʹ��iΪ�������������� 
	*/  
	 
	private static final void swap(int x,int y){
		  
		  RBHeap temp = heap[x];
		  heap[x] = heap[y];
		  heap[y] =temp;
	  }
	 
   private static void heapify(int index ,int length){
			  
			  
	 int next = index;
			  
	 int left = leftChild(index);
			  
	 int right = rightChild(index);
			  
	 if(left < length && heap[left].data < heap[next].data){
				  
			next = left;
	   }
			  
	 if(right < length && heap[right].data < heap[next].data){
				  
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

	 public static void heapify(int index){
		  
		  heapify(index,heap.length);
		  
	  } 
	/**  
	* BUILD_MINHEAP����������A�е����ݽ�����С�� 
	*/  
	public static void buildMINHEAP()  
	{  
		int length = heap.length;
    
        for(int i = parent(length-1) ; i >= 0 ;i--){
   	 
   	         heapify(i);
        }
   	 
    } 
	 
	  
	  
	/* 
	3��ά��k��Ԫ�ص���С�ѣ�ԭ����������2������һ�£� 
	��������Ϊk����С�Ѵ洢�����ں�����б�������k���������������Ǽ�������k���������ѷ�ʱO��k���� 
	Ȼ������ѣ���ʱO��logk��������k1>k2>...kmin��kmin��ΪС��������СԪ�أ��� 
	������������������ÿ�α���һ��Ԫ��x����Ѷ�Ԫ�رȽϣ���x>kmin������¶ѣ���ʱlogk�������򲻸��¶ѡ� 
	�����������ܷ�ʱO��k*logk+��n-k��*logk��=O��n*logk���� 
	�˷����������ڶ��У����ҵȸ������ʱ�临�ӶȾ�Ϊlogk���� 
	*/  
	  
	//�������RBTree  
	public static void inOrderTraverse(RBNode node)    
	{  
	    if (node == null)    
	    {    
	        return;    
	    }    
	    else    
	    {    
	        inOrderTraverse(node.left);    
	        if (node.data > heap[0].data) // ��ǰ�ڵ�data������С�ѵ���СԪ��ʱ�����¶�����  
	        {  
	            heap[0].data = node.data;  
	            heap[0].key = node.key;  
	            heapify(0);  
	        }  
	        inOrderTraverse(node.right);    
	    }  
	}   
	  
	public static void rbDestroy(RBNode node)  
	{  
	    if (null == node)  
	    {  
	        return;  
	    }  
	    else  
	    {  
	        rbDestroy(node.left);  
	        rbDestroy(node.right);   
	        node = null;  
	    }  
	}

}
