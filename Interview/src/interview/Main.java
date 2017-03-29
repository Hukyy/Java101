package interview;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	
	public static HashMap<String, String[]> functions = new HashMap<String, String[]>();
	
	public static boolean CompositionCheck(String composition){
		//String reversed = new StringBuilder(composition).reverse().toString();
		String[] decomp = composition.split(" \\. ");
		
		int len = decomp.length;
		for (int i=0;i<len/2;i++){
			String temp = decomp[i];
			decomp[i]=decomp[len-i-1];
			decomp[len-i-1]=temp;
		}
		for (int i=0;i<len-1;i++){
			String from = functions.get(decomp[i].trim())[1];
			String to = functions.get(decomp[i+1].trim())[0];
			if (!from.equalsIgnoreCase(to)){
				return false;
			}
		}
		return true;
	}
	
	public static void addFunction(String function){
    	String[] NameAndArgument = function.split("::");
    	String[] FromTo = NameAndArgument[1].split("->");
    	String Name  = NameAndArgument[0].trim();
    	FromTo[0] = FromTo[0].trim();
    	FromTo[1] = FromTo[1].trim();
    
    	
    	functions.put(Name, FromTo);
    	
	}
	
	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
		    
		String function = "";
		 
	    int count= scanner.nextInt();
	    scanner.nextLine();
	    for (int i=0; i<count;i++){
	    	function = scanner.nextLine();
	    	
	    	addFunction(function);
	    }
	    scanner.nextLine();
	    String composition = scanner.nextLine();
	    System.out.println(CompositionCheck(composition));
	    


	   
	    scanner.close();
	}

}
