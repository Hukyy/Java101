package bst;

public class Main {

	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
		bst.insert(100);
		bst.insert(200);
		bst.insert(199);
		bst.insert(180);
		bst.insert(170);
		bst.insert(175);
		bst.insert(174);
		bst.insert(176);
		bst.insert(160);
		bst.insert(150);
		//bst.remove(100);
		System.out.println(bst);
		System.out.println(bst.lowerBound(170));
//		System.out.println(bst.find(100));
		
	}

}
