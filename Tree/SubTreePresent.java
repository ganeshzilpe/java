

import java.util.ArrayList;


public class SubTreePresent {

	Node root;
	int count = 0, sum =0;
	public SubTreePresent()
	{
		root= null;
	}

	public Node insert(Node node, int value)
	{
		if(node == null)
		{
			node = new Node(value);
			if(root == null)
				root = node;
			return node;
		}
		if(value <= node.data)
			node.left = insert(node.left, value);
		else
			node.right = insert(node.right, value);
		return node;
	}
	
	public boolean isSubtree(Node rootOfTree, Node rootOfSubTree)
	{
		//empty subtree is a subtree of tree
		if(rootOfSubTree == null)
			return true;
		//there will be no subtree of empty tree
		if(rootOfTree == null)
			return false;
		return (equals(rootOfTree, rootOfSubTree) || isSubtree(rootOfTree.left, rootOfSubTree)) ||  isSubtree(rootOfTree.right, rootOfSubTree);
			
	}
	
	public boolean equals(Node n1, Node n2)
	{
		//if we have provided node of maintree as a root of subtree
		if(n1 == n2)
			return true;
		
		//if subtree is at the end and maintree has more descendents, this means till now parents of n1 and n2 are matched each other
		if(n2 == null && n1 != null)
			return true;
			
		if(n1 == null || n2 == null)
			return false;
		if(n1.data != n2.data)
			return false;
		return equals(n1.left, n2.left)&& equals(n1.right, n2.right);
	}
	
	public Node getRoot()
	{
		return root;
	}
	
	class Node
	{
		int data;
		Node left, right;
		Node(int value)
		{
			data = value;
			left = right = null;
		}
	}
	
	public static void main(String [] args)
	{
		//maintree
		int [] num = {4,2,4,6,7,3,2,6,65,23,45,123,895,33,12,55};//{50,25,75,10,35,60,100,5,20,30,45,55,70,90,102};
		SubTreePresent mainTree = new SubTreePresent();


		for(int x : num)
			mainTree.insert(mainTree.getRoot(), x);
		System.out.println();
		
		//subTree
		int [] num2 = {6,6,7,65};
		SubTreePresent subTree = new SubTreePresent();


		for(int x : num2)
			subTree.insert(subTree.getRoot(), x);
		
		//check subTree is present in mainTree or not
		System.out.println("Subtree present in main tree: "+mainTree.isSubtree(mainTree.getRoot(), subTree.getRoot()));
		
	}


}
