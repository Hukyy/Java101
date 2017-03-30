package array;

import java.util.ArrayList;

public class Arr {

	public static String toString(int[] a){
		// method that recieves an integer array and returns a string of the array, separated by ","
		StringBuilder sb = new StringBuilder();
		for (int i = 0;i < a.length-1;i++){
			sb.append(a[i]+", ");
		}
		sb.append(a[a.length-1]);
		return sb.toString();
	}
	public static void sort(int[] a){
		if (a.length<=1){
			return;
		}
		for (int i=1;i<a.length;i++){
			int j = i;
			while (j>0 && a[j]< a[j-1]){
				int temp = a[j];
				a[j] = a[j-1];
				a[j-1] = temp;
				j--;
			}
		}
	}

	public static int[] reverse(int[] a){
		//method that recieves an integer array and returns the reversed array
		int length = a.length;
		int[] result = new int[length];
		for (int i=0;i<length;i++){
			result[length-1-i] = a[i];
		}
		return result;
	}
	public static String join(int[] a, String glue){
		//method that recieves an array of int and a string and returns a String of the array elements with 'glue' between them
		StringBuilder sb = new StringBuilder();
		for (int i = 0;i < a.length-1;i++){
			sb.append(a[i]);
			sb.append(glue);
		}
		sb.append(a[a.length-1]);
		return sb.toString();
	}
	public static int sum(int[] a) {
		//method that returns the sum of all the elements in 'a'
		int result = 0;
		for (int x : a){
			result+=x;
		}
		return result;
	}
	public static int[] range(int a, int b){
		//that returns an array of int with elements in the range between a and b
		int result[] = new int[b-a];
		int index = 0;
		while (a<b){
			result[index++] = a++;
		}
		return result;
	}
	public static int[] filterOdd(int[] a){
		// method that returns an array with only the Odd numbers from 'a'
		ArrayList<Integer> list = new ArrayList<>();
		for (int x : a){
			if (x%2 == 1){
				list.add(x);
			}
		}
		int[] result = new int[list.size()];
		for (int i=0; i<list.size();i++){
			result[i] = list.get(i);
		}
		return result;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,2,3,4,5,6,7};
		int[] b = {7,6,15,11,312,5,9,3,2,1};
		//System.out.println(Arr.toString(b));
		Arr.sort(b);
		//System.out.println(Arr.toString(b));
		//System.out.println(Arr.toString(Arr.reverse(a)));

		//		int [] c = {3,1,-40,200,5};
		//		  System.out.println(Arr.join(c, ": "));
		//		  System.out.println(Arr.join(c, "->"));

		//		  int [] d = Arr.range(10, 20);
		//		  System.out.println(Arr.join(d, ", "));

		int [] e = {2,3,4,8,9,11,13,15};
		int [] temp = Arr.filterOdd(e);
		System.out.println(Arr.join(temp, ", "));

	}



}
