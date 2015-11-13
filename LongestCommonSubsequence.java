/*
 *HackerRank : A subsequence is a sequence that can be derived from another sequence by deleting some elements without 
 * changing the order of the remaining elements. Longest common subsequence 
 * (LCS) of 2 sequences is a subsequence, with maximal length, which is common to both the sequences. 
 *
 * Sample Input
 *
 * 5 6 (number of items in each coming string or arrays)
 * 1 2 3 4 1
 * 3 4 1 2 1 3
 * Sample Output (Sample output may have multiple ansers (1 2 3 , 3 4 1 , 1 2 1), show at least one)
 * 1 2 3
 */

//longest common subsequence
//O(m*n)
public class LongestCommonSubsequence {

	int array1[];
	int array2[];
	int table [][];
	char symbolTable [][];
	String result;

	public static void main(String[] args) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		Scanner scanner = new Scanner(System.in);
		int length1 = scanner.nextInt();
		int length2 = scanner.nextInt();
		lcs.array1 = new int[length1];
		lcs.array2 = new int[length2];

		for(int i=0; i<length1; i++)
			lcs.array1[i]  = scanner.nextInt();
		for(int i=0; i<length2; i++)
			lcs.array2[i] = scanner.nextInt();
		lcs.table = new int[lcs.array1.length+1][lcs.array2.length+1];
		lcs.symbolTable = new char[lcs.array1.length+1][lcs.array2.length+1];
		lcs.result = "";
		lcs.findLongestSubsequence();
		int m = lcs.array1.length;
		int n = lcs.array2.length;
		lcs.printLCS(m, n);
		System.out.println(lcs.result);
	}
	public void findLongestSubsequence()
	{
		int i = 0, j=0;
		for(i=0; i<= array1.length; i++)
			table[i][0] = 0;
		for(i=0; i<= array2.length; i++)
			table[0][i] = 0;
		for(i=1; i< table.length; i++)
		{
			for(j=1; j< table[0].length; j++)
			{
				if(array1[i-1] == array2[j-1])
				{
					table[i][j] = table[i-1][j-1]+1;
					symbolTable[i][j] = '/';

				}
				else
				{
					if(table[i][j-1] > table [i-1][j])
					{
						table[i][j] = table[i][j-1];
						symbolTable[i][j] = '-';
					}
					else
					{
						table[i][j] = table[i-1][j];
						symbolTable[i][j] = '|';
					}
				}

			}
		}

	}
	public void printLCS(int m, int n)
	{
		if( m == 0 || n == 0)
			return;
		if(symbolTable[m][n] == '/')
		{
			printLCS(m-1, n-1);
			result = result + array1[m-1]+" ";
		}
		else if(symbolTable[m][n] == '|')
			printLCS(m-1, n);
		else
			printLCS(m, n-1);

	}
}
