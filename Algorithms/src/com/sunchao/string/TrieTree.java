package com.sunchao.string;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrieTree {
	
	private static Vertex root = new Vertex();
	
	
	public static List<String> listAllWords()
	{
		List<String> words = new ArrayList<String>();
		
		Vertex[]  edges = root.edges;

		for(int i = 0 ; i < edges.length ; i++)
		{
			if(edges[i] != null)
			{
				String newWord = "" +(char)('a' + i);
				
				depthFirstSearchWords(words,edges[i],newWord);
			}
			
		}
		
		return words;
	}
	
	
	private static void depthFirstSearchWords(List<String> words , Vertex vertex,String wordSegment)
	{
		if(vertex.wordCounts != 0)
		{
			words.add(wordSegment);
		}
			
		Vertex[]  edges = vertex.edges;
		
		for(int i  = 0 ; i < edges.length ;  i++)
		{
			
			if(edges[i] !=  null)
			{
				String newWord = wordSegment + (char)('a' + i);
				
				depthFirstSearchWords(words, edges[i] , newWord);
			}
		}
	}
	
	public static void addWord(String word)
	{
	   	
		 addWord(root,word);
	}
	
	private static void addWord(Vertex vertex, String word)
	{
		
		
		if(word.length() == 0)
			
			vertex.wordCounts++;
		
		
		else{
			
			char c = word.charAt(0);
			
			c = Character.toLowerCase(c);
			
			int index =  c -'a';
			
			if(vertex.edges[index] ==  null)
			{
				
				vertex.edges[index]  = new Vertex();
			}
			
			addWord(vertex.edges[index], word.substring(1));
		}
		
	}
	
	public static int countWord(String wordSegment)
	{
		return countWord(root,wordSegment);
		
	}
	
	public static String getMaxMatchWord(String word)
	{
		String s = "";
		
		String temp = "";
		
		char[] charArray = word.toCharArray();
		
		Vertex vertex = root;
		
		for(int i = 0 ; i < charArray.length ;i++)
		{
			char c = charArray[i];
			
			c = Character.toLowerCase(c);
			
			int index = c -'a';
			
			if(vertex.edges[index] == null)
			{
				if(vertex.wordCounts != 0)
				{
					return  s ;
				}
				else
					return null;
				
			}
			else{
				
				if(vertex.wordCounts != 0)
					
					 temp = s ;
			    
				s += c;
				
				vertex = vertex.edges[index];
					
			}
		}
		
		if(vertex.wordCounts == 0)
			
			 return temp ;
		
		return s;
		
	}
	
	private static int countWord(Vertex vertex, String wordSegment)
	{
	        if(wordSegment.length() == 0)
	        {
	        	
	        	return vertex.wordCounts;
	        }
		
	        char c = wordSegment.charAt(0);
	        
	        int index = c -'a';
	        
	        if(vertex.edges[index] == null)
	        {    
	        	return  0;
	        }else{
	        	
	        	return countWord(vertex.edges[index],wordSegment.substring(1));
	        }
	        	
	}
	
	
	
	
    protected static class Vertex
    
    {
		
	   protected int wordCounts;
	   
	   
	   protected Vertex[]  edges;
	   
	   Vertex(){
		   
		   this.wordCounts = 0;
		   
		   
		   this.edges = new Vertex[26];
	   }
		
	}
    public static void main(String args[]) // Just used for test
    {
        TrieTree trie = new TrieTree();
        trie.addWord("abcedfddddddd");
        trie.addWord("a");
        trie.addWord("ba");
        trie.addWord("abce");
        trie.addWord("abce");
        trie.addWord("abced");
        trie.addWord("abcedfdddd");
        trie.addWord("abcef");
 
        String maxMatch = trie.getMaxMatchWord("abcedfdddd");
        System.out.println(maxMatch);
        List<String> list = trie.listAllWords();
        Iterator listiterator = list.listIterator();
        while (listiterator.hasNext()) {
            String s = (String) listiterator.next();
            System.out.println(s);
        }
 
       
         int count1 = trie.countWord("abce");
        
         System.out.println("countWords:" + count1);
 
    }
}
