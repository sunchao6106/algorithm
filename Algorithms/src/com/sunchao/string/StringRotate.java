package com.sunchao.string;

public class StringRotate {

	public static String stringRotate1(String a,int m){
		
		if(a.length() == 0 )  return a;
		
		char[] charArray = a.toCharArray();
		
		int n = charArray.length;
		
		if(m % n == 0)  return a;
		
        m = positiveMethod(m,n);
		
		if((m % n) <= 0)  return a;
		int p0 =0 , p1=m;
		
		while(true){
		   
			swap(charArray,p0,p1);
			
			p0++;
			
			if(p1 < n-1)
				p1++;
			else
				break;
			
		} 
		
		int r = m - (n % m);
		
		while(r-- > 0){
			
			int t = p0;
			
			char tmp =charArray[p0];
			
			while(t < p1){
				
				charArray[t]=charArray[t+1];
				
				t++;
			}
			charArray[p1] =tmp;
		}
		
		return new String(charArray);
	
	}
	
	public static String stringRotate5(String a,int m){
		
		if(a.length() ==  0 || m <= 0) return a;
		
		char[] charArray = a.toCharArray();
		
		int length = charArray.length;
		
		if(m % length <= 0)  return a;
		
		invert(charArray,0,0+m-1);
		invert(charArray,m,length-1);
		invert(charArray,0,length-1);
		return new String(charArray);
		
		
	}
	
	private static void invert(char[] charArray,int start,int end){
		
		char tmp;
		
		while(start < end){
			
			tmp = charArray[start];
			charArray[start] = charArray[end];
			charArray[end]=tmp;
			start++;
			end--;
		}
		
		
	}
	
	//gcb算法的运用 ,n times updates
	public static String stringRotate4(String a,int m){
		
		if(a.length() == 0 || m <=0)  return  a;
		
		char[] charArray = a.toCharArray();
		
		int length = charArray.length;
		
		if(m % length <= 0)  return a;
		
		int  numOfGroup = gcb(length,m);
		int elementsOfSub = length / numOfGroup;
		int j;
		
		for(int i = 0 ; i < numOfGroup ; i++){
			
			char tmp = charArray[i];
			
			for(j = i ; j < elementsOfSub-1 ; j++){
				
				charArray[(i+j*m) % length] = charArray[(i+(j+1)*m) % length];
			}
			charArray[(i+j*m) % length] =tmp;
		}
		return new String(charArray);
	}
	private static int gcb(int x,int y){
		
		if(x < y){
			
			x=x^y;
			y=x^y;
			x=x^y;
		}
		
		int r = x % y;
		if(r == 0)  return y;
		return gcb(y,r);
	}
	public static String stringRotate2(String a,int m){
		
		if(a.length() == 0)  return a;
		
		char[] charArray = a.toCharArray();
		
		int n = charArray.length;
		
		m = positiveMethod(m,n);
		
		if((m % n) <= 0)  return a;
		
		int p0 = 0,p1 = m;
		
		int bound;
		
		while(p1 + m - 1 < n){
			
			bound = m;
			
			while(bound-- > 0){
				
				swap(charArray,p0,p1);
				p0++;
				p1++;
				
			}
			int r = n - p1;
			
			while(r-- > 0){
				
				int i = p1;
				
				while(i > p0){
					
					swap(charArray,i,i-1);
					
				    i--;	
				}
				p0++;
				p1++;
			}
		}
		
		return new String(charArray);
	}
	
	private static int positiveMethod(int m,int n){
		
		return ((m % n) + n) % n;
	}
	
	
	public static String stringRotate(String a, int m){
		
		if(a.length() == 0 || m<=0) return a;
		
		
		char[]  charArray =a.toCharArray();
		
		int n = charArray.length;
		
		if(m % n <= 0)  return a;// k < 0 or k = size;
		
		
		int p1 = 0,p2 = m;
		
		int k = (n -m) - (n % m);
		
		while(k-- > 0){
			
			swap(charArray,p1,p2);
			p1++;
			p2++;
			
		}
		
		int r = n - p2;
		
		while(r-- > 0){
			
			int j =p2;
			
			while(j > p1){
				
				swap(charArray,j,j-1);
				j--;
			}
			p1++;
			p2++;
		}
		
		return  new String(charArray);
	}
	
	private static final void swap(char[] array,int x,int y){
		
		char tmp = array[x];
		array[x] = array[y];
		array[y] = tmp;
	}
	
	public static void main(String args[]){
		//int s = positiveMethod(-3,11);
		String  s =stringRotate5("abcdefghijk",4);
		System.out.println(s);
	}
}
