import java.io.*;
import java.lang.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        Solution task = new Solution();
        System.out.println(task.countSegments("Hello,   my   name   is John"));
    }

    public int countSegments(String s) {
        StringTokenizer tokenizer = new StringTokenizer(s);
        int result = 0;
        while (tokenizer.hasMoreTokens()) {
            result++;
            tokenizer.nextToken();
        }
        return result;
    }

}
