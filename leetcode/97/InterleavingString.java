public class InterleavingString {
    
    public static void main(String[] args) {
        Solution s = new Solution();
        /*System.out.println(s.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(s.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(s.isInterleave("a", "b", "a"));*/
        System.out.println(s.isInterleave(
            "baababbabbababbaaababbbbbbbbbbbaabaabaaaabaaabbaaabaaaababaabaaabaabbbbaabbaabaabbbbabbbababbaaaabab",
            "aababaaabbbababababaabbbababaababbababbbbabbbbbababbbabaaaaabaaabbabbaaabbababbaaaababaababbbbabbbbb",
            "babbabbabbababbaaababbbbaababbaabbbbabbbbbaaabbabaababaabaaabaabbbaaaabbabbaaaaabbabbaabaaaabbbbababbbababbabaabababbababaaaaaabbababaaabbaabbbbaaaaabbbaaabbbabbbbaaabaababbaabababbbbababbaaabbbabbbab"
        ));
    }

    static class Solution {
        byte[][] m;
        
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() == 0)
                return s2.equals(s3);
            if (s2.length() == 0)
                return s1.equals(s3);
            if (s1.length() + s2.length() != s3.length())
                return false;
            m = new byte[s1.length() + 1][s2.length() + 1];
            return isInterleave(s1, s2, s1.length(), s2.length(), s3) == 1;
        }
        
        private byte isInterleave(String s1, String s2, int l1, int l2, String s3) {
            if (m[l1][l2] > 0)
                return m[l1][l2];
            if (l1 == 0)
                return m[l1][l2] = (byte) (s2.substring(0, l2).equals(s3.substring(0, l2)) ? 1 : 2);
            else
            if (l2 == 0)
                return m[l1][l2] = (byte) (s1.substring(0, l1).equals(s3.substring(0, l1)) ? 1 : 2);
            if (s1.charAt(l1 - 1) == s3.charAt(l1 + l2 - 1)) {
                m[l1][l2] = isInterleave(s1, s2, l1 - 1, l2, s3);
                if (m[l1][l2] == 1)
                    return m[l1][l2];
            }
            if (s2.charAt(l2 - 1) == s3.charAt(l1 + l2 - 1))
                m[l1][l2] = isInterleave(s1, s2, l1, l2 - 1, s3);
            else
                m[l1][l2] = 2;
            return m[l1][l2];
        }
    }

}
