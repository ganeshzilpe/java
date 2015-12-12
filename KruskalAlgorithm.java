import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
 * Reference: https://www.hackerrank.com/challenges/kruskalmstrsub
 * Input Format:
 *  First line has two integers N, denoting the number of nodes in the graph and M, denoting the number of edges in the graph.
	The next M lines each consist of three space separated integers x y r, where x and y denote the two nodes between which the undirected edge exists, r denotes the weight of edge between the corresponding nodes.
	The last line has an integer S, denoting the starting node. 
	
	Output Format:
	Print a single integer denoting the total weight (sum of weights of all edges in the MST) of the Really Special SubTree i.e. Minimum Spanning Tree.
	
	Sample Input
	4 6
	1 2 5
	1 3 3
	4 1 6
	2 4 7
	3 2 4
	3 4 5
	1
	
	Sample Output
	
	12

 */
public class KruskalAlgorithm {
	//for recording the edges of each node in the minimum spanning tree 
	static Map <Integer, ArrayList<Integer [] >> nodeArrays = new HashMap<Integer, ArrayList<Integer [] >>();
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		int nodes = scanner.nextInt();
		int edges = scanner.nextInt();
		//map records the edges of each distance <distance, list of edges of same distance> 
		Map <Integer, ArrayList> map = new HashMap<Integer, ArrayList>();

		for(int i=0; i<nodes; i++)
		{
			nodeArrays.put((Integer)i, new ArrayList<Integer [] >());
		}
		for(int i=0; i< edges; i++)
		{
			Integer [] array = new Integer[2];
			array[0] = scanner.nextInt()-1;
			array[1] = scanner.nextInt()-1;
			int distance = scanner.nextInt();
			ArrayList <Integer []> list = map.get(distance);
			if(list == null || list.isEmpty())    
			{   
				list = new ArrayList <Integer []>();
				list.add(array);
			}
			else
			{
				list.add(array);
			}
			map.put(distance, list);
		}
		
		//sort the keys of instance i.e. sort the map in increasing order of distances
		List sortedKeys=new ArrayList(map.keySet());
		Collections.sort(sortedKeys);
		
		//list of nodes of minimum spanning tree
		List resultNodes = new ArrayList();
		int total = 0;
		int edgeCount = 0;

		for(int i=0; i< sortedKeys.size(); i++)
		{
			Integer key = (Integer)sortedKeys.get(i);
			ArrayList <Integer [] > arrays = map.get(key);
			for(int j=0; j<arrays.size(); j++)
			{
				Integer [] array1 = (Integer [] )arrays.get(j);
				if(!(resultNodes.contains(array1[0]) && resultNodes.contains(array1[1])))
				{
					if(!resultNodes.contains(array1[0]) && resultNodes.contains(array1[1]))
						resultNodes.add(array1[0]);
					else if(!resultNodes.contains(array1[1]) && resultNodes.contains(array1[0]))
						resultNodes.add(array1[1]);
					else
					{
						resultNodes.add(array1[0]);
						resultNodes.add(array1[1]);
					}
					total += key;
					edgeCount++;
					ArrayList <Integer [] > l1 = nodeArrays.get((Integer)array1[0]);
					Integer [] a = new Integer[2];
					a[0] = array1[0];
					a[1] = array1[1];
					l1.add(a);
					nodeArrays.put((Integer)array1[0], l1);

					l1 = nodeArrays.get((Integer)array1[1]);
					a = new Integer[2];
					a[0] = array1[1];
					a[1] = array1[0];
					l1.add(a);
					nodeArrays.put((Integer)array1[1], l1);

				}
				else
				{
					//if both nodes are in resultNodes, then before adding current edge, check will it make loop
					//if not then add it
					if(!checkLoop(array1[0], array1[1]))
					{
						total += key;
						edgeCount++;
						ArrayList <Integer [] > l1 = nodeArrays.get((Integer)array1[0]);
						Integer [] a = new Integer[2];
						a[0] = array1[0];
						a[1] = array1[1];
						l1.add(a);
						nodeArrays.put((Integer)array1[0], l1);

						l1 = nodeArrays.get((Integer)array1[1]);
						a = new Integer[2];
						a[0] = array1[1];
						a[1] = array1[0];
						l1.add(a);
						nodeArrays.put((Integer)array1[1], l1);
					}
				}
				if(edgeCount == nodes+1)
					break;

			}
			
			if(edgeCount == nodes+1)
				break;
		}
		System.out.println(total);
	}

	public static boolean checkLoop(int currentNode, int targetNode)
	{
		return next_edge(currentNode, targetNode, -1);
	}

	public static boolean next_edge(int currentNode, int targetNode, int previousNode)
	{
		if(currentNode == targetNode)
			return true;
		boolean result = false;
		ArrayList <Integer []> list = nodeArrays.get((Integer)currentNode);
		for(int i=0; i< list.size(); i++)
		{
			Integer [] array = list.get(i);
			//getting the same edge for from which we come here i.e. edge from previous node is equal to edge from currentNode
			if(previousNode != -1 && previousNode == array[1])
				continue;
			result = next_edge(array[1], targetNode, array[0]);
			if(result)
				return true;
		}
		return false;
	}
}
