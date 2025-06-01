import java.util.*;

class Solution {
    static class Info {
        int cnt;
        String word;
        Info(int cnt, String word) {
            this.cnt = cnt;
            this.word = word;
        }
    }
    
    static int bfs(String begin, String target) {
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(0, begin));
 
        boolean[] visited = new boolean[n];
        
        int answer = 0;
        
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            
            if (info.word.equals(target)) {
                answer = info.cnt;
                break;
            }
            
            for (int i=0; i<n; i++) {
                if (visited[i]) {
                    continue;
                }
                String cur = wordList.get(i);
                int diff = 0;
                for (int j=0; j<cur.length(); j++) {
                    if (cur.charAt(j) != info.word.charAt(j)) {
                        diff++;
                    }
                }
                if (diff == 1) {
                    visited[i] = true;
                    queue.add(new Info(info.cnt+1, cur));
                }
            }
        }
        
        return answer;
    }
    
    static int n;
    static ArrayList<String> wordList = new ArrayList<>();
    static final int INF = 1_000_000_000;
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        for (String word : words) {
            wordList.add(word);
        }
        
        if (!wordList.contains(target)) {
            return 0;
        }
        
        n = words.length;

        answer = bfs(begin, target);
        
        return answer;
    }
}