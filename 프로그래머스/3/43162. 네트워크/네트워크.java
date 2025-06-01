import java.util.*;

class Solution {
    
    static void dfs(int x, int n, int[][] computers) {
        visited[x] = true;
        
        for (int i=0; i<n; i++) {
            if (!visited[i] && computers[x][i] == 1) {
                dfs(i, n, computers);
            }
        }
    }
    
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                dfs(i, n, computers);
                answer++;
            }
        }
        
        return answer;
    }
}