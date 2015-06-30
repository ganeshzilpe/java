import java.util.ArrayList;
import java.util.List;


class Node
{
	int data;
	Node left;
	Node right;
	public Node( int value)
	{
		data = value;
		left = null;
		right = null;
	}
}
public class BinarySearchTree {
	Node root;
	int sum = 0;
	int count =0;
	boolean flag = false;
	public BinarySearchTree() {
		root = null;
	}
	
	public void setSum(int v)
	{
		sum = v;
	}

	public Node getRoot()
	{
		return root;
	}

	public Node insert(Node node, int data)
	{
		if(node == null)
		{
			node = new Node(data);
			if(root == null)
				root = node;
			return node;
		}
		if(data <= node.data)
			node.left = insert(node.left, data);
		else
			node.right = insert(node.right, data);
		return node;
	}

	public void preOrder(Node node)
	{
		if(node == null)
			return;
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	public void printAllPathWithSum(Node node, int sum, ArrayList<Integer> list)
	{
//		if(!list.isEmpty() && flag)
//			return;
//		if(node != null && list.isEmpty())
//			flag= false;
		if(node == null)
			return;
		if(node.data<=sum)
		{
			list.add(node.data);
			if(node.data == sum)
				print(list);
			else
			{
				if(node.left == null && node.right == null)
				{
					int index = list.lastIndexOf(node.data);
					list.remove(index);
				}
				else
				{
					count++;
					printAllPathWithSum(node.left, sum - node.data, list);
					printAllPathWithSum(node.right, sum - node.data, list);
					count--;
				}
			}
		}
		if(count != 0)
			return ;
		
			
		printAllPathWithSum(node.left, this.sum, new ArrayList());
		if(count != 0)
			return;
		printAllPathWithSum(node.right, this.sum, new ArrayList());
		

	}
	public void print(List list)
	{
		System.out.println("Next path");
		for(int i=0; i<list.size(); i++)
			System.out.print(Integer.toString((Integer)list.get(i)) + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		int[] input={50,25,75,10,35,60,100,5,20,30,45,55,70,90,102};//{1, 2, 3, 4, 5, 10, 11, 6, 7, 8, 9, 12, 13, 14, 15};
		for(int i:input)
			bst.insert(bst.getRoot(), i);
		System.out.println("Preorder");
		System.out.println();
		bst.preOrder(bst.getRoot());
		ArrayList<Integer> list = new ArrayList<Integer>();
		bst.setSum(75);
		bst.printAllPathWithSum(bst.getRoot(), 75, list);
	}

}
