package Questions;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Help_GenerateCombinations {
	
	public static ArrayList<Character> createArrayList(Character ... elements) {
		  ArrayList<Character> list = new ArrayList<Character>(); 
		  for (Character element : elements) {
		    list.add(element);
		  }
		  return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	        ArrayList<Character> characters = createArrayList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
					'c', 'd', 'e', 'f');        
	        ArrayList<String> allStrings = new ArrayList<String>();
	        String filename = "comp.txt";
	        
	        
	        try {
				// Create file
	        	FileWriter fw = new FileWriter(filename);
				PrintWriter fos = new PrintWriter(filename);
				//BufferedWriter bos = new BufferedWriter(fos);
	        	//FileOutputStream outoutbox= new FileOutputStream("outbox.txt");  
	        	//PrintStream p=new PrintStream(outoutbox);
				
				
				
	        for (Character i : characters) {
	            for (Character j : characters) {
	                for (Character k : characters) {
	                    for (Character l : characters) {
	                    	for (Character m : characters) {
	    	                    for (Character n : characters) {
	                        String s = "d1bd8747a743d838418e23ba1a" + i +j + k + l + m +n  ;
	                        fos.println(s);
	                        //p.println(s);
	                        System.out.println(s);
	                        allStrings.add(s);
	    	                    }
	                    	}
	                    }
	                }
	            }
	        }
	        /*for(String str: allStrings) {
	        	  fw.write(str);
	        	  System.out.println();
	        	}
	        	fw.close();*/
	        //System.out.println(allStrings.size()); // 16777216 combinations
	        		fos.close();
	        //p.close();
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}
	}
	
	
}