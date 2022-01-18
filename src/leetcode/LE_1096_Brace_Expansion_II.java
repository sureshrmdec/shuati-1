package leetcode;

import java.util.*;

public class LE_1096_Brace_Expansion_II {
    /**
     * Under the grammar given below, strings can represent a set of lowercase words. Let R(expr) denote the set of words
     * the expression represents.
     *
     * The grammar can best be understood through simple examples:
     *
     * Single letters represent a singleton set containing that word.
     * R("a") = {"a"}
     * R("w") = {"w"}
     * When we take a comma-delimited list of two or more expressions, we take the union of possibilities.
     * R("{a,b,c}") = {"a","b","c"}
     * R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
     * When we concatenate two expressions, we take the set of possible concatenations between two words where the first
     * word comes from the first expression and the second word comes from the second expression.
     * R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
     * R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
     * Formally, the three rules for our grammar:
     *
     * For every lowercase letter x, we have R(x) = {x}.
     * For expressions e1, e2, ... , ek with k >= 2, we have R({e1, e2, ...}) = R(e1) ∪ R(e2) ∪ ...
     * For expressions e1 and e2, we have R(e1 + e2) = {a + b for (a, b) in R(e1) × R(e2)}, where + denotes concatenation,
     * and × denotes the cartesian product.
     * Given an expression representing a set of words under the given grammar, return the sorted list of words that the
     * expression represents.
     *
     * Example 1:
     * Input: expression = "{a,b}{c,{d,e}}"
     * Output: ["ac","ad","ae","bc","bd","be"]
     *
     * Example 2:
     * Input: expression = "{{a,z},a{b,c},{ab,z}}"
     * Output: ["a","ab","ac","z"]
     * Explanation: Each distinct word is written only once in the final answer.
     *
     * Constraints:
     * 1 <= expression.length <= 60
     * expression[i] consists of '{', '}', ','or lowercase English letters.
     * The given expression represents a set of words based on the grammar given in the description.
     *
     * Hard
     *
     * https://leetcode.com/problems/brace-expansion-ii/
     */

    /**
     * Compare with LE_1087_Brace_Expansion, now, we have nested brackets and Cartesian product rules.
     */

    class Solution {
        Set<String> set = new HashSet<String>();
        /**
         * Use set to de-dup
         */
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<String>();
        List<String> res;

        public List<String> braceExpansionII(String expression) {
            stack.push(expression);
            helper();
            /**
             * Convert set to list
             */
            res = new ArrayList<>(set);
            Collections.sort(res);
            return res;
        }

        private void helper(){
            while(!stack.isEmpty()) {
                String str = stack.pop();
                /**
                 * No more brackets in current string, meaning no need to do further expansion, we find one result.
                 */
                if (str.indexOf('{') == -1) {
                    set.add(str);
                    continue;
                }

                /**
                 * Find the bottom level of brackets pair substring, the one that does not have nested brackets.
                 * For example, for "{{a,b},{b,c}}", it will find "{a, b}".
                 */
                int i = 0, l = 0, r = 0;
                while (str.charAt(i) != '}') {
                    if (str.charAt(i++) == '{') //??
                        l = i - 1;
                }
                r = i;

                String before = str.substring(0, l);
                String after = str.substring(r + 1, str.length());

                /**
                 * Get token
                 */
                String[] tokens = str.substring(l + 1, r).split(",");

                for (String s : tokens) {
                    /**
                     * expand and push into stack for further processing
                     */
                    sb.setLength(0);
                    stack.push(sb.append(before).append(s).append(after).toString());
                }
            }
        }
    }
}
