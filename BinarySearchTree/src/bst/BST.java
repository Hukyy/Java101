package bst;

public class BST<T extends Comparable<T>> implements BinarySearchTreeInterface<T>{
	
	private class Node<T>{
		private Node<T> left;
		private T value;
		private Node<T> right;
		
		public Node(T val){
			left = null;
			this.value=val;
			right = null;
		}
	}
	
	private Node<T> root;
	private Node<T> fakeRoot;
	private void insert(Node<T> current, T element){
		if (current.value.compareTo(element)<0){
			if (current.right != null){
				insert(current.right,element);
			}
			else {
				current.right = new Node<T>(element);
			}
		}
		else {
			if (current.left != null){
				insert(current.left,element);
			}
			else {
				current.left = new Node<T>(element);
			}
		}
	}
	
	private Node<T> findParent(Node<T> current, T element){
		if (current == null)
		{
			return null;
		}
		Node<T> next = current.value.compareTo(element) < 0 ? current.right : current.left;
		if (next.value.compareTo(element) == 0){
			return current;
		}
		return findParent(next, element);		
	}
	
	private Node<T> findMin(Node<T> current){
		current = current.right;
		while (current.left != null){
			current = current.left;
		}
		return current;
	}
	
	private Node<T> find (Node<T> current, T element){
		if (current == null){
			return null;
		}
		if (current.value.compareTo(element) == 0){
			return current;
		}
		Node<T> next = current.value.compareTo(element) < 0 ? current.right : current.left;
		return find(next,element);
		
	}
	
	private void hasNoChild(Node<T> parent, Node<T> current, T element){
		if (parent.value.compareTo(element)<0){
			parent.right = null;
		}
		else {
			parent.left = null;
		}
	}
	
	private void hasLeftChild(Node<T> parent, Node<T> current, T element){
		if (parent.value.compareTo(element)<0){
			parent.right = current.left;
		}
		else {
			parent.left = current.left;
		}
	}
	
	private void hasRightChild(Node<T> parent, Node<T> current, T element){
		if (parent.value.compareTo(element)<0){
			parent.right = current.right;
		}
		else {
			parent.left = current.right;
		}
	}
	
	private void hasLeftAndRightChild(/*Node<T> parent,*/ Node<T> current, T element){
		Node<T> min = findMin(current);
		Node<T> parentOfMin = findParent(current, min.value);
		current.value = min.value;
		if (current == parentOfMin){
			current.right=null;
		}
		else {
			parentOfMin.left=null;
		}
		
		
	}
	
	private void delete(Node<T> start, T element){
		Node<T> parent = findParent(start, element);
		Node<T> current = find(start,element);
		
		//has no child
		if(current.left == null && current.right == null){
			hasNoChild(parent, current, element);
		}
		//has only left child
		else if (current.left != null && current.right == null){
			hasLeftChild(parent, current, element);
		}
		//has only right child
		else if (current.left == null && current.right != null){
			hasRightChild(parent, current, element);
		}
		//has both children
		else {
			hasLeftAndRightChild(current, element);
		}
		
	}
	
	public BST(){
		root = null;
	}

	@Override
	public void insert(T element) {
		if (root == null){
			root = new Node<T>(element);
		}
		else {
			insert(root,element);
		}
		
	}

	@Override
	public void remove(T element) {
		if (root == null) {
			return;
		}
		
		if (root.value.compareTo(element)==0){
			if (root.left == null && root.right == null){
				root = null;
			}
			else if (root.left == null && root.right != null){
				root = root.right;
			}
			else if (root.left != null && root.right == null){
				root = root.left;
			}
			else {
				hasLeftAndRightChild(root, element);
			}
		}
		
		else {
			delete(root,element);
		}
	}
	
	private Boolean equalTrees(Node<T> our, Node<T> their){
		if (our==null && their != null || our!=null && their == null){
			return false;
		}
		if (our == null && their == null){
			return true;
		}
		if (our.value.compareTo(their.value) != 0){
			return false;
		}
		return equalTrees(our.left, their.left) && equalTrees(our.right,their.right);
	}
	
	public Boolean equalTrees(BST<T> other){
		return equalTrees(root, other.root);
	}

	@Override
	public Boolean find(T element) {
		Node<T> gotIt = find(root,element);
		return gotIt != null;
	}
	
	private void traverse(Node<T> current, StringBuilder sb){
		if (current != null){
			traverse(current.left, sb);
			sb.append(current.value + " ");
			traverse(current.right,sb);
			
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		traverse(root,sb);
		return sb.toString();
	}
	
	
	
}
