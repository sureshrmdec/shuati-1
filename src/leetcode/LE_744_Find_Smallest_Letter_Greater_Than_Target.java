package leetcode;

public class LE_744_Find_Smallest_Letter_Greater_Than_Target {
    /**
         Given a list of sorted characters letters containing only lowercase letters, and given a target letter target,
         find the smallest element in the list that is larger than the given target.

         Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

         Examples:
         Input:
         letters = ["c", "f", "j"]
         target = "a"
         Output: "c"

         Input:
         letters = ["c", "f", "j"]
         target = "c"
         Output: "f"

         Input:
         letters = ["c", "f", "j"]
         target = "d"
         Output: "f"

         Input:
         letters = ["c", "f", "j"]
         target = "g"
         Output: "j"

         Input:
         letters = ["c", "f", "j"]
         target = "j"
         Output: "c"

         Input:
         letters = ["c", "f", "j"]
         target = "k"
         Output: "c"

         Note:
         letters has a length in range [2, 10000].
         letters consists of lowercase letters, and contains at least 2 unique letters.
         target is a lowercase letter.

         Easy
     */

    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0;
        int r = n;

        while (l < r) {
            int m = l + (r - l) / 2;

            /**
             * we want to find is letters[l] > target, therefore, two cases:
             * letters[m] <= target , "=" should come with "<" since it is NOT the range we want to find
             * letters[m] > target
             */
            if (letters[m] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        //return letters[l % n];
        return l >= n ? letters[0] : letters[l];//!!!
    }
}