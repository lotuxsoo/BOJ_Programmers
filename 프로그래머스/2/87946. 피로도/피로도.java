import java.util.*;

class Solution {
    static int k;
    static int[][] dungeons;
    static boolean[] visited;
    static int MAX_VAL = Integer.MIN_VALUE;
    
    static void backtrack(int cnt, int k) {
        MAX_VAL = Math.max(MAX_VAL, cnt);
        
        for (int i=0; i<dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                backtrack(cnt+1, k - dungeons[i][1]);
                visited[i] = false;
            }
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        this.k = k;
        this.dungeons = dungeons;
        
        visited = new boolean[dungeons.length];
        
        backtrack(0, k);

        answer = MAX_VAL;
        
        return answer;
    }
}