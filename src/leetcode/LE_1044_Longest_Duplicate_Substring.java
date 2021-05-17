package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LE_1044_Longest_Duplicate_Substring {
    /**
     * Given a string S, consider all duplicated substrings: (contiguous) substrings
     * of S that occur 2 or more times.  (The occurrences may overlap.)
     *
     * Return any duplicated substring that has the longest possible length.
     * (If S does not have a duplicated substring, the answer is "".)
     *
     * Example 1:
     *
     * Input: "banana"
     * Output: "ana"
     *
     * Example 2:
     *
     * Input: "abcd"
     * Output: ""
     *
     * Note:
     *
     * 2 <= S.length <= 10^5
     * S consists of lowercase English letters.
     *
     * Hard
     */

    /**
     * Binary Search
     * https://leetcode.com/problems/longest-duplicate-substring/discuss/290871/Python-Binary-Search
     *
     * Binary Search in range 1 and N, so it's O(logN)
     * Rolling hash O(N)
     * Overall O(NlogN)
     * SpaceO(N)
     */
    class Solution1 {
        private long q = (1 << 31) - 1; // a large prime
        private int R = 256; // radix

        // compute hash for key[0..m-1]
        private long hash(String key, int m) {
            long h = 0;
            for (int j = 0; j < m; j++) {
                h = (R * h + key.charAt(j)) % q;
            }
            return h;
        }

        private boolean compare(String s, int i1, int i2, int m) {
            for (int i = 0; i < m; i++) {
                if (s.charAt(i1 + i) != s.charAt(i2 + i)) return false;
            }
            return true;
        }

        /**
         * testing if string s has two same substring with length m.
         */
        private int test(String s, int m) {
            Map<Long, List<Integer>> map = new HashMap<>();
            long h = hash(s, m);
            map.put(h, new ArrayList<>());
            map.get(h).add(0);

            long RM = 1; // R^(m-1) % q
            for (int i = 1; i <= m - 1; i++) {
                RM = (R * RM) % q;
            }

            // NOTE i is the ending index of current string
            for (int i = m; i < s.length(); i++) {
                // remove leading digit, add trailing digit, check for match
                h = (h + q - RM * s.charAt(i - m) % q) % q;
                h = (h * R + s.charAt(i)) % q;

                int startIndex = i - m + 1;
                if (map.containsKey(h)) {
                    for (int prev : map.get(h)) {
                        if (compare(s, startIndex, prev, m)) return startIndex;
                    }
                } else {
                    map.put(h, new ArrayList<>());
                }
                map.get(h).add(startIndex);
            }

            return -1;
        }

        public String longestDupSubstring(String S) {
            int lo = 0, hi = S.length();

            // binary search to find SMALLEST length of string that cannot pass test
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                int index = test(S, mid);

                if (index < 0) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            // lo-1 is the LARGEST length of string that CAN pass test
            int checkLen = lo - 1;
            if (checkLen <= 0) return "";

            int start = test(S, checkLen);

            return S.substring(start, start + checkLen);
        }
    }

    class Solution2 {
        public String longestDupSubstring(String S) {
            // edge case
            if (S == null) {
                return null;
            }
            // binary search the max length
            int min = 0;
            int max = S.length() - 1;
            int mid;
            while (min < max - 1) {
                mid = (min + max) / 2;
                if (searchForLength(S, mid) != null) {
                    min = mid;
                } else {
                    max = mid - 1;
                }
            }

            String str = searchForLength(S, max);
            if (str != null) {
                return str;
            } else {
                return searchForLength(S, min);
            }
        }

        String searchForLength(String str, int len) {
            // rolling hash method
            if (len == 0) {
                return "";
            } else if (len >= str.length()) {
                return null;
            }

            Map<Long, List<Integer>> map = new HashMap<>();    // hashcode -> list of all starting idx with identical hash
            long p = (1 << 31) - 1;  // prime number
            long base = 256;
            long hash = 0;
            for (int i = 0; i < len; ++i) {
                hash = (hash * base + str.charAt(i)) % p;
            }
            long multiplier = 1;
            for (int i = 1; i < len; ++i) {
                multiplier = (multiplier * base) % p;
            }

            // first substring
            List<Integer> equalHashIdx = new ArrayList<Integer>();
            equalHashIdx.add(0);
            map.put(hash, equalHashIdx);
            // other substrings
            int from = 0;
            int to = len;
            while (to < str.length()) {
                hash = ((hash + p - multiplier * str.charAt(from++) % p) * base + str.charAt(to++)) % p;
                equalHashIdx = map.get(hash);
                if (equalHashIdx == null) {
                    equalHashIdx = new ArrayList<Integer>();
                    map.put(hash, equalHashIdx);
                } else {
                    for (int i0: equalHashIdx) {
                        if (str.substring(from, to).equals(str.substring(i0, i0 + len))) {
                            return str.substring(i0, i0 + len);
                        }
                    }
                }
                equalHashIdx.add(from);
            }
            return null;
        }
    }
}
