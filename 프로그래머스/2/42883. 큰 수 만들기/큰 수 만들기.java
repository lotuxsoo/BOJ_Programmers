import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int n = number.length();
        char[] ch = number.toCharArray();
        
        for (int i=0; i<n; i++) {
            while (!stack.isEmpty() && (ch[i] > ch[stack.peek()]) && (k>0)) {
                stack.pop();
                k--;
            }
            stack.push(i); // 인덱스를 저장
        }
        
        StringBuilder sb = new StringBuilder();
        if (k == 0) {
            while (!stack.isEmpty()) {
                sb.insert(0, ch[stack.pop()]);
            }
        } else {
            while (!stack.isEmpty() && (k>0)) {
                stack.pop();
                k--;
            }
            while (!stack.isEmpty()) {
                sb.insert(0, ch[stack.pop()]);
            }
        }
        
        answer = sb.toString();
        
        return answer;
    }
}