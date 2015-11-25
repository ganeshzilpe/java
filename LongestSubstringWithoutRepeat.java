//Longest Substring Without Repeating Characters
//eg.  For "bbbbb" the longest substring is "b", with the length of 1.

public class LongestSubstringWithoutRepeat {
    public int lengthOfLongestSubstring(String s) {
        boolean [] presentArray = new boolean [256];
        Arrays.fill(presentArray, false);
        String result = "";
        String temp = "";
        for(int i=0; i<s.length(); i++)
        {
            if(presentArray[s.charAt(i)] == false)
            {
                temp +=s.charAt(i);
                presentArray[s.charAt(i)] = true;
            }
            else
            {
                if(temp.length() > result.length())
                {
                    result = temp;
                }
                //as duplicate is found, them we start the remove the characters 
                //from the temp string till last occurence of this duplicate char
                int number = temp.indexOf(s.charAt(i));
                int startNumber = i - temp.length() + number + 1;
                Arrays.fill(presentArray, false);
                
                for(int j= startNumber; j<=i; j++)
                {
                    presentArray[s.charAt(j)] = true;
                }
                //assign the substring from next char to last occurence of this duplicate char till i
                temp = s.substring(startNumber, i+1);
            }
        }
        if(temp.length() > result.length())
        {
            result = temp;
        }
        return result.length();
        
    }
}
