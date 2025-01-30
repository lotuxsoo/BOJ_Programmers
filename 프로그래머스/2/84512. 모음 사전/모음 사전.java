import java.util.*;

class Solution {
    static String[] str = {"A","E","I","O","U"};
    static int cnt = 0;
    static int result = 0;
    
    static void backtrack(StringBuilder cur, String target) {
        if (cur.toString().equals(target)) {
            result = cnt;
            return;
        }
        
        if (cur.length() == 5) {
            return;
        }
        
        for (int i=0; i<str.length; i++) {
            cur.append(str[i]);
            cnt++;
            backtrack(cur, target);
            cur.deleteCharAt(cur.length()-1);
        }
    }
    
    public int solution(String word) {
        int answer = 0;
        
        backtrack(new StringBuilder(), word);
        
        answer = result;
        
        return answer;
    }
}