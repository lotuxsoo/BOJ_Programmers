import java.util.*;

class Solution {
    
    static int n;
    static ArrayList<String> wordList = new ArrayList<>();
    static final int INF = 1_000_000_000;
    static Map<String, Integer> map = new HashMap<>();
    
    static int bfs(String begin, String target) {
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);
 
        boolean[] visited = new boolean[n];
        int answer = 0;
        
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            
            if (cur.equals(target)) {
                answer = map.getOrDefault(cur, 0);
                break;
            }
            
            for (int i=0; i<n; i++) {
                if (visited[i]) {
                    continue;
                }
                
                String word = wordList.get(i);
                int diff = 0;
                for (int j=0; j<word.length(); j++) {
                    if (word.charAt(j) != cur.charAt(j)) {
                        diff++;
                    }
                }
                
                if (diff == 1) {
                    visited[i] = true;
                    // cur까지의 depth+1
                    map.put(word, map.getOrDefault(cur, 0)+1);
                    queue.add(word);
                }
            }
        }
        
        return answer;
    }
    
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