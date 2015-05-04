package com.sunchao.topk;

import com.sunchao.topk.HashtableTopK.HashNode;

public class RedBlackTopK {
	
	private static RBNode root;
	/**  
	* 左旋   
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
	* 右旋   
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
	* 红黑树的3种插入情况   
	* 用z表示当前结点, p[z]表示父母、p[p[z]]表示祖父, y表示叔叔. 
	*/  
	public static RBNode  rbInsert_Rebalance(RBNode node)    
	{    
	    RBNode parent, gparent, uncle, tmp,newNode =null;  //父母p[z]、祖父p[p[z]]、叔叔y、临时结点*tmp    
	      
	    while ((parent = node.parent) !=null && parent.color == RB_COLOR.RED)    
	    { // parent 为node的父母，且当父母的颜色为红时    
	        gparent = parent.parent;   // gparent为祖父    
	          
	        if (parent == gparent.left)  // 当祖父的左孩子即为父母时,其实上述几行语句，无非就是理顺孩子、父母、祖父的关系。  
	        {  
	            uncle = gparent.right; // 定义叔叔的概念，叔叔y就是父母的右孩子。    
	            if (uncle != null && uncle.color == RB_COLOR.RED) // 情况1：z的叔叔y是红色的    
	            {    
	                uncle.color = RB_COLOR.BLACK;   // 将叔叔结点y着为黑色    
	                parent.color =RB_COLOR. BLACK;  // z的父母p[z]也着为黑色。解决z，p[z]都是红色的问题。    
	                gparent.color = RB_COLOR.RED;      
	                node = gparent;     // 将祖父当做新增结点z，指针z上移俩层，且着为红色。    
	                // 上述情况1中，只考虑了z作为父母的右孩子的情况。    
	            }    
	            else                     // 情况2：z的叔叔y是黑色的，    
	            {       
	                if (parent.right == node)  // 且z为右孩子    
	                {    
	                    newNode = rb_LeftRotate(parent); // 左旋[结点z，与父母结点]    
	                    tmp = parent;    
	                    parent = node;    
	                    node = tmp;     // parent与node 互换角色    
	                }    
	                // 情况3：z的叔叔y是黑色的，此时z成为了左孩子。    
	                // 注意，1：情况3是由上述情况2变化而来的。    
	                // ......2：z的叔叔总是黑色的，否则就是情况1了。    
	                parent.color = RB_COLOR.BLACK;   // z的父母p[z]着为黑色    
	                gparent.color = RB_COLOR.RED;    // 原祖父结点着为红色    
	                newNode = rb_RightRotate(gparent); // 右旋[结点z，与祖父结点]    
	            }    
	        }     
	          
	        else     
	        {         
	            // 这部分是特别为情况1中，z作为左孩子情况，而写的。    
	            uncle = gparent.left;  // 祖父的左孩子作为叔叔结点。[原理还是与上部分一样的]    
	            if (uncle != null && uncle.color == RB_COLOR.RED)  // 情况1：z的叔叔y是红色的    
	            {    
	                uncle.color = RB_COLOR.BLACK;    
	                parent.color = RB_COLOR.BLACK;    
	                gparent.color = RB_COLOR.RED;    
	                node = gparent;           // 同上  
	            }    
	            else                               // 情况2：z的叔叔y是黑色的，    
	            {    
	                if (parent.left == node)  // 且z为左孩子    
	                {    
	                    newNode = rb_RightRotate(parent);  // 以结点parent、root右旋    
	                    tmp = parent;    
	                    parent = node;    
	                    node = tmp;       // parent与node 互换角色    
	                }     
	                // 经过情况2的变化，成为了情况3.    
	                parent.color = RB_COLOR.BLACK;    
	                gparent.color = RB_COLOR.RED;    
	                newNode = rb_LeftRotate(gparent);   // 以结点gparent和root左旋    
	            }    
	        }    
	    }    
	      
	    newNode.color = RB_COLOR.BLACK; // 根结点，不论怎样，都得置为黑色。    
	    return newNode;      // 返回根结点。    
	}  
	  
	/**  
	* 红黑树查找结点   
	* rb_search_auxiliary：查找   
	* RBNode rb_search：返回找到的结点   
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
	* 返回上述rb_search_auxiliary查找结果   
	*/  
	public static RBNode rbSearch(int key, RBNode root,boolean flag)    
	{    
	    return rbSearchAuxiliary(key, root,flag);    
	}    
	  
	/**  
	* 红黑树的插入   
	*/  
	public static RBNode rb_Insert(int key, int data, RBNode root)    
	{    
	    RBNode parent = null;  
	    RBNode node = null;    
	      
	    parent = null;    
	    if ((node = rbSearchAuxiliary(key, root, false)) != null)  // 调用RB_SearchAuxiliary找到插入结点的地方    
	    {    
	        node.data++; // 节点已经存在data值加1  
	        return root;    
	    }    
	    parent = rbSearchAuxiliary(key, root, true);
	    node = new RBNode(key, data);  // 分配结点    
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
	      
	    return rbInsert_Rebalance(node);   // 插入结点后，调用RB_Insert_Rebalance修复红黑树的性质    
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
	* MAX_HEAPIFY函数对堆进行更新，使以i为根的子树成最大堆 
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
	* BUILD_MINHEAP函数对数组A中的数据建立最小堆 
	*/  
	public static void buildMINHEAP()  
	{  
		int length = heap.length;
    
        for(int i = parent(length-1) ; i >= 0 ;i--){
   	 
   	         heapify(i);
        }
   	 
    } 
	 
	  
	  
	/* 
	3、维护k个元素的最小堆，原理与上述第2个方案一致， 
	即用容量为k的最小堆存储最先在红黑树中遍历到的k个数，并假设它们即是最大的k个数，建堆费时O（k）， 
	然后调整堆（费时O（logk））后，有k1>k2>...kmin（kmin设为小顶堆中最小元素）。 
	继续中序遍历红黑树，每次遍历一个元素x，与堆顶元素比较，若x>kmin，则更新堆（用时logk），否则不更新堆。 
	这样下来，总费时O（k*logk+（n-k）*logk）=O（n*logk）。 
	此方法得益于在堆中，查找等各项操作时间复杂度均为logk）。 
	*/  
	  
	//中序遍历RBTree  
	public static void inOrderTraverse(RBNode node)    
	{  
	    if (node == null)    
	    {    
	        return;    
	    }    
	    else    
	    {    
	        inOrderTraverse(node.left);    
	        if (node.data > heap[0].data) // 当前节点data大于最小堆的最小元素时，更新堆数据  
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
