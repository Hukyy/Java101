package functions;

import java.util.Scanner;

public class Parser {
	  
	public static int f1(int x) {
		return f2(x) + f3(x);
	}
	
	public static int f2(int x) {
		return 2 * x;
	}
	
	public static int f3(int x) {
		return x + 1;
	}
	
	public static int f4(int x) {
		return 2 * f1(x);
	}
	
	  public static int evaluate(String formula, int x) {
		  int result = x;
			String[] functions = formula.split(" \\. ");
			for (int i = functions.length - 1; i > -1; i--) {
				switch(functions[i]) {
				case "f1": result=f1(result); break;
				case "f2": result=f2(result); break;
				case "f3": result=f3(result); break;
				case "f4": result=f4(result); break;
				}
			}
			return result;
	  }
	  
	  public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    
	    String input = scanner.nextLine();
	    int x = scanner.nextInt();
	    
	    System.out.println(evaluate(input, x));

	  }

	}
