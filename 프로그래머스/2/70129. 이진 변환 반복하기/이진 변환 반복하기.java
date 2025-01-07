import java.util.*;

class Solution {
    static int cnt = 0;
    
    static String toBin(int c) {
        Deque<Integer> stack = new ArrayDeque<>();
        while (c > 0) {
            stack.push(c % 2);
            c /= 2;
        }
        StringBuilder sb = new StringBuilder();
        
        while (!stack.isEmpty()) {
            sb.append(String.valueOf(stack.pop()));
        }

        return sb.toString();
    }
    
    static int change(String x) {
        cnt++;
        int zero = 0;
        int one = 0;
        for (int i=0; i<x.length(); i++) {
            if (x.charAt(i) == '0') {
                zero++;
            } else {
                one++;
            }
        }
        
        if (one != 1) {
            x = toBin(one);
            zero += change(x);
        }
        return zero;
    }
    
    public int[] solution(String s) {
        int[] answer = {};
        
        int zeros = change(s);
        
        answer = new int[]{cnt,zeros};
        
        return answer;
    }
}