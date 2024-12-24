import java.util.*;

class Solution {
    
    static boolean[] visited;
    
    static void DFS(int x, int[][] computers) {
        for (int i=0; i<computers[x].length; i++) {
            if (!visited[i] && computers[x][i]==1) {
                visited[i] = true;
                DFS(i, computers);
            }    
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;

        visited = new boolean[n];
        
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                DFS(i, computers);
                answer++;
            }
        }
        
        return answer;
    }
}