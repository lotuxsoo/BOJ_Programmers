import java.util.*;

class Solution {
    
    static void dfs(int depth, String cur, String target) { 
        if (cur.equals(target)) {
            minVal = Math.min(minVal, depth);
            return;
        }
        
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                String word = wordList.get(i);
                int cnt = 0;
                for (int j=0; j<word.length(); j++) {
                    if (word.charAt(j) != cur.charAt(j)) cnt++;
                }
                if (cnt == 1) {
                    visited[i] = true;
                    dfs(depth+1, word, target);
                    visited[i] = false;
                }
            }
        } 
    }
    
    static int n;
    static ArrayList<String> wordList = new ArrayList<>();
    static boolean[] visited;
    static int minVal = 1_000_000_000;
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        for (String word : words) {
            wordList.add(word);
        }
        
        if (!wordList.contains(target)) {
            return 0;
        }
        
        n = words.length;
        visited = new boolean[n];
        dfs(0, begin, target);
        
        answer = minVal;
        
        return answer;
    }
}