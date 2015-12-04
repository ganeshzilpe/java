
class Node
{
	int data;
	Node left;
	Node right;
	
	public Node(int number)
	{
		data = number;
		left = null;
		right = null;
	}
}

class BinaryTree
{
	Node root;
	Node head;
	Node prev;
	public BinaryTree()
	{
		root  = null;
		head = null;
		prev = null;
	}
	
	public void addNode(int number)
	{
		Node node = new Node(number);
		if(root == null)
		{
			root = node;
			return;
		}
		else
		{
			Node traversedNode = root;
			Node prevNode = root;
			while(true)
			{
				System.out.println(traversedNode.data);
				if(number >= traversedNode.data)
				{
					prevNode = traversedNode;
					traversedNode = traversedNode.right;
					if(traversedNode == null)
					{
						prevNode.right = node;
						return;
					}
				}
				else
				{
					prevNode = traversedNode;
					traversedNode = traversedNode.left;
					if(traversedNode == null)
					{
						prevNode.left = node;
						return;
					}
				}
			}
			
		}
	}
	
	public void printInfix(Node node)
	{
		int count = 0;
		if(node == head)
		{
			if(count == 0)
			count++;
			else
			return;
		}
		if(node == null)
			return;
		printInfix(node.left);
		System.out.println(node.data);
		printInfix(node.right);
	}
	
	public void printLinkedList(Node head)
	{
		System.out.println("Print the LinkedList: ");
		if(head == null)
			return;
		Node node1 = head;
		do
		{
			System.out.println(node1.data);
			node1 = node1.right;
		}while(node1 != head);
	}
	
	public void  convertToDoublyLinkedList(Node node)
	{
		if(node == null)
			return ;
		convertToDoublyLinkedList(node.left);
		
		if(prev == null)
		{
			head = node;
			prev = node;
		}
		else
		{
			prev.right = node;
			node.left = prev;
			prev = node;
		}
		
		convertToDoublyLinkedList(node.right);
			
	}
	
}

public class ConvertBinaryTreeInDoublyLinkedList
{
	public static void main(String args[])
	{
		BinaryTree bt = new BinaryTree();
		bt.addNode(4);
		bt.addNode(2);
		bt.addNode(1);
		bt.addNode(3);
		bt.addNode(5);
		System.out.println("Print Binary Tree (Infix): ");
		bt.printInfix(bt.root);
		Node prev = new Node(-1);
		bt.convertToDoublyLinkedList(bt.root);
		bt.head.left = bt.prev;
		bt.prev.right = bt.head;
		bt.printLinkedList(bt.head);
	}
}
