import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Deque<String> que = new ArrayDeque<>();
        for (String str : s.split("")) {
            if (!que.isEmpty()) {
                if (str.equals("(")) {
                    que.push("(");
                } else {
                    if (que.peek().equals("(")) {
                        que.pop();
                    }
                }
            } else {
                if (str.equals(")")) {
                    return false;
                } else {
                    que.push("(");
                }
            }
        }
        
        if (que.size() > 0) return false;

        return answer;
    }
}