

/*
 * For adhering to the sum property of tree, every node has data value must be equal to sum of its left and right
 * child. 
 * 
 * Tree 1:
 * 							10
 * 						/		 \
 * 						8		  2
 * 					  /	  \     /  
 * 					 3     5   2
 * 			This tree follows sum property
 * 
 * * Tree 2:
 * 							10
 * 						/		 \
 * 						8		  2
 * 					  /	  \     /   \
 * 					 3     5   2     1
 * 			This tree does not follows sum property as 2 != 2 + 1
 */
public class CheckSumPropertyInTree {

	Node root;
	
	public CheckSumPropertyInTree() {
		root = null;
	}
	public void setRoot(Node r)
	{
		root = r;
	}
	public boolean checkSumProperty(Node node)
	{
		int leftData = 0, rightData = 0;
		if(node == null)
			return true;
		if(node.left == null && node.right == null)
			return true;
		if(node.left == null)
			leftData = 0;
		else
			leftData = node.left.data;
		if(node.right == null)
			rightData = 0;
		else
			rightData = node.right.data;
		
		return (node.data == leftData+rightData) && checkSumProperty(node.left) && checkSumProperty(node.right);
	}
	
	public static void main(String[] args) {
		CheckSumPropertyInTree tree1 = new CheckSumPropertyInTree();
		Node node = new Node(10);
		tree1.setRoot(node);
		tree1.root.left = new Node(8);
		tree1.root.right = new Node(2);
		tree1.root.left.left = new Node(3);
		tree1.root.left.right = new Node(5);
		tree1.root.right.left = new Node(2);
		System.out.println("Does tree follows sum property: "+tree1.checkSumProperty(tree1.root));
		
		
		CheckSumPropertyInTree tree2 = new CheckSumPropertyInTree();
		Node node2 = new Node(10);
		tree2.setRoot(node2);
		tree2.root.left = new Node(8);
		tree2.root.right = new Node(2);
		tree2.root.left.left = new Node(3);
		tree2.root.left.right = new Node(5);
		tree2.root.right.left = new Node(2);
		tree2.root.right.right = new Node(1);
		System.out.println("Does tree follows sum property: "+tree2.checkSumProperty(tree2.root));
		
	}
	
}
/*
class Node
{
	int data;
	Node left, right;
	Node(int value)
	{
		data = value;
		left=null;
		right = null;
	}
	
}*/
