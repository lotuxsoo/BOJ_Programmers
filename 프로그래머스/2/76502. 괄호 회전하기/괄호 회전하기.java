import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        Map<String,String> map = new HashMap<>();
        map.put("(",")");
        map.put("[","]");
        map.put("{","}");
        
        for (int i=0; i<s.length(); i++) {
            Deque<String> stack = new ArrayDeque<>();
            String s1 = s.substring(i,s.length()) + s.substring(0,i);
            boolean flag = false;
            
            for (String str : s1.split("")) {
                if (stack.isEmpty()) {
                    if (!map.containsKey(str)) {
                        flag = true;
                        break;
                    }
                    stack.push(str);
                } else {
                    if (map.get(stack.peek()).equals(str)) {
                        stack.pop();
                    } else {
                        if (!map.containsKey(str)) {
                            flag = true;
                            break;
                        }
                        stack.push(str);
                    }
                }
            }
            
            if (!flag && stack.isEmpty()) answer++;
        }
        
        return answer;
    }
}