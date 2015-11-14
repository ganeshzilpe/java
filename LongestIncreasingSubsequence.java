import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
 * https://www.hackerrank.com/challenges/longest-increasing-subsequent =>
 *Longest Increasing Subsequence Problems
 *
 * The task is to find the length of the longest subsequence in a given array of integers such that all elements of the 
 * subsequence are sorted in ascending order. For example, the length of the LIS for { 15, 27, 14, 38, 26, 55, 46, 65, 85 } is 6 and the longest increasing subsequence is {15, 27, 38, 55, 65, 85}. 
 * Input Format
 * In the first line of input, there is a single number N.
 * In the next N lines input the value of a[i]. 
 *Output Format
 * In a single line, output the length of the longest increasing sub-sequence.

Sample Input
5
2
7
4
3
8

Sample Output
3
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numbers = scanner.nextInt();
        int array [] = new int[numbers];
        for(int i=0; i< numbers; i++)
            array[i] = scanner.nextInt();
            
        printLongest(array);
    }
    
    public static void printLongest(int array[])
    {
        Map<Integer, ArrayList> table = new HashMap<Integer, ArrayList>();
        for(int i=0; i<array.length; i++)
        {
            if(i == 0)
            {
                ArrayList list = new ArrayList();
                list.add(array[0]);
                table.put(new Integer(0),list);
            }
            else
                table.put(new Integer(i), new ArrayList());
        }
        
        for(int i=1; i< array.length; i++)
        {
            List list1 = table.get(new Integer(i));
            for(int j=0; j<i; j++)
            {
                
                List list2 = table.get(new Integer(j));
                if(array[j]<array[i] && list1.size() < list2.size()+1)
                {
                    list1 = list2;
                }
            }
            List finalList = new ArrayList(list1);
            finalList.add(array[i]);
            table.put(new Integer(i), (ArrayList)finalList);
        }
        
        int result = 0;
        List resultList = null;
        for(int i=0; i< array.length; i++)
        {
            List list = table.get(new Integer(i));
            
            if(result < list.size())
            {
                result = list.size();
                resultList = list;
            }
        }
        System.out.println("result size: "+result);
        System.out.println(Arrays.asList(resultList.toArray()));
    }
}
