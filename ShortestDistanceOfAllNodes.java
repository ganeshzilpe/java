/* 
Referemce: https://www.hackerrank.com/challenges/bfsshortreach/copy-from/15711775
*Given an undirected graph consisting of N nodes (labelled 1 to N) where a specific given node S represents the start position and an edge between any two nodes is of length 6 units in the graph.

It is required to calculate the shortest distance from start position (Node S) to all of the other nodes in the graph.

Note 1: If a node is unreachable , the distance is assumed as −1. 
Note 2: The length of each edge in the graph is 6 units.

Sample Input

2 -> number of test cases
4 2 -> number of nodes and number of edges present in graphs -> first test case
1 2 -> present edge
1 3 -> present edge
1   -> start node
3 1  -> second test case
2 3
2
Sample Output

6 6 -1
-1 6
*/  

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ShortestDistanceOfAllNodes {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        for(int i=0; i<number; i++)
        {
            
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
                distances[node1][node2] = 6;
                distances[node2][node1] = 6;
            }
            int start = scanner.nextInt();
            table.put(start-1, 0);
            Queue <Integer>queue = new LinkedList<Integer>();
            queue.add(start-1);
            while(!queue.isEmpty())
            {
                int node = queue.remove();
                for(j=0; j<nodes; j++)
                {
                    // no edge present between node to itself
                    if(node==j)
                        continue;
                    //no edge present
                    if(distances[node][j] == -1)
                        continue;
                    //already node is visited
                    if(visited[j] == 1)
                        continue;
                    //relax the edge
                    if((Integer)table.get(j) > ((Integer)(table.get(node))+distances[node][j]))
                        table.put(j, (Integer)(table.get(node))+distances[node][j]);
                    
                    queue.add(j);
                }
                visited[node] = 1;
            }
            
            for(j=0; j<table.size(); j++)
            {
                if(j== start-1)
                    continue;
                if((Integer)table.get(j) == Integer.MAX_VALUE)
                    System.out.print("-1 ");
                else
                    System.out.print((Integer)table.get(j)+" ");
            }
            System.out.println();
        }
    }
    
    
    
}
