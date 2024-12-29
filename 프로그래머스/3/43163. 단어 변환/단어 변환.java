import java.io.*;
import java.util.*;

class Solution {
    
    static boolean cango(String from, String to) {
        int cnt = 0;
        for (int i=0; i<from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) cnt++;
        }
        return cnt == 1;
    }
    
    static int BFS(String begin, String target, String[] words) {
        int ans = 0;
        Queue<String> que = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        que.add(begin);
        
        while (!que.isEmpty()) {
            for (int i=0; i<que.size(); i++) { // 현재 들어있는 큐만큼만 반복
                String now = que.poll();
                
                if (now.equals(target)) {
                    return ans;
                }
                
                for (int j=0; j<words.length; j++) {
                    if (!visited[j] && cango(now, words[j])) {
                        que.add(words[j]);
                        visited[j] = true;
                    }
                }
            }
            ans++;
        }
            
        return ans;
    }
    
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean flag = false;
        for (String word : words) {
            if (word.equals(target)) {
                flag = true;
                break;
            }
        }
        if (!flag) return 0;
        
        answer = BFS(begin, target, words);
        
        return answer;
    }
}