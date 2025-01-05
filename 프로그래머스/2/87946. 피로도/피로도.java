import java.util.*;

class Solution {
    
    static int MAX_VAL = Integer.MIN_VALUE;
    static boolean[] visited;
    
    static void permute(int cnt, int k, int[][] dungeons) {
        MAX_VAL = Math.max(MAX_VAL, cnt);

        for (int i=0; i<dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                permute(cnt+1, k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        visited = new boolean[dungeons.length];
        
        permute(0, k, dungeons);
        
        answer = MAX_VAL;
        
        return answer;
    }
}