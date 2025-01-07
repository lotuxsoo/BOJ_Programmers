import java.util.*;

class Solution {
    static String[] words;
    static boolean[] visited;
    
    static boolean canAdd(String to, String from) {
        if (from.length() != to.length()) return false;
        
        int cnt = 0;
        for (int i=0; i<from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) cnt++;
        }

        return cnt == 1;
    }
    
    static int BFS(String begin, String target) {
        int cnt = 0;
        Queue<String[]> queue = new LinkedList<>();
        queue.add(new String[]{begin,"0"});
        
        while (!queue.isEmpty()) {
            String[] current = queue.poll();
            String now = current[0];
            int level = Integer.parseInt(current[1]);

            if (now.equals(target)) return level;
            
            for (int i=0; i<words.length; i++) {
                if (!visited[i] && canAdd(words[i], now)) {
                    visited[i] = true;
                    queue.add(new String[]{words[i], String.valueOf(level + 1)});
                }
            }
        }
        return 0;
    }
    
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        this.words = words;
        
        boolean flag = false;
        for (String word : words) {
            if (word.equals(target)) {
                flag = true;
                break;
            }
        }
        if (!flag) return 0;
        
        visited = new boolean[words.length];
            
        answer = BFS(begin, target);
        
        return answer;
    }
}