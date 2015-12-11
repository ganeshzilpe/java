import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
 * Reference: https://www.hackerrank.com/challenges/primsmstsub/copy-from/15715182
 * Given a graph which consists of several edges connecting the N nodes in it. 
	It is required to find a subgraph of the given graph with the following properties:
	
	The subgraph contains all the nodes present in the original graph.
	The subgraph is of minimum overall weight (sum of all edges) among all such subgraphs.
	It is also required that there is exactly one, exclusive path between any two nodes of the subgraph.
	Sample Input

	5 6 -> number of nodes and number of edges
	1 2 3 -> first two numbers are endpoints of edge and last number is weight of edge
	1 3 4
	4 2 6
	5 2 2
	2 3 5
	3 5 7
	1 -> start node
	Sample Output
	
	15
 */
public class PrimsAlgorithm {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
            int nodes = scanner.nextInt();
            int edges = scanner.nextInt();
            int distances[][] = new int[nodes][nodes];
            int visited [] = new int[nodes];
            
            Arrays.fill(visited, 0);
            Hashtable table = new Hashtable();
            int j = 0, k=0;
            
            for(;j<nodes; j++)
                table.put(j, Integer.MAX_VALUE);
            for(j=0; j<nodes; j++)
            {
                for(k=0; k<nodes; k++)
                {
                    distances[j][k] = -1;
                }
            }
            for(j=0; j<edges; j++)
            {
                int node1 = scanner.nextInt()-1;
                int node2 = scanner.nextInt()-1;
                int distance = scanner.nextInt();
                distances[node1][node2] = distance;
                distances[node2][node1] = distance;
            }
            int start = scanner.nextInt()-1; 
            table.put(start, 0);
            visited[start] = 1;
            Vector result = new Vector();
            result.add(start);
        while(result.size() != nodes)
        {
            int min = Integer.MAX_VALUE;
            int nextNode = -1;
            for(int i =0; i< result.size(); i++)
            {
                int node = (Integer)result.get(i);
                for(j=0; j<nodes; j++)
                {
                    if(node==j)
                        continue;
                    if(distances[node][j] == -1)
                        continue;
                    if(visited[j] == 1)
                        continue;
                    if(distances[node][j] <min)
                    {
                        min = distances[node][j];
                        nextNode = j;
                        
                    }
                }
            }
            if(nextNode != -1)
            {    
                visited[nextNode] = 1;
                table.put(nextNode, min);
                result.add(nextNode);
            }
        }
        int total = 0;
        for(int i =0; i< table.size(); i++)
        {
            total += (Integer)table.get(i);
        }
        System.out.println(total);
        
    }
}
