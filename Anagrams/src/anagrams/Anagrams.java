package anagrams;

import java.util.Arrays;
import java.util.Scanner;

public class Anagrams {

	public static boolean areAnagrams(String a, String b) {
		char[] first = a.toLowerCase().toCharArray();
		char[] second = a.toLowerCase().toCharArray();
		Arrays.sort(first);
		Arrays.sort(second);
		a = String.valueOf(first);
		b = String.valueOf(second);
		return a.equals(b);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String a = scanner.next();
		String b = scanner.next();

		if (areAnagrams(a, b)) {
			System.out.println("ANAGRAMS");
		} else {
			System.out.println("NOT ANAGRAMS");
		}
	}
}