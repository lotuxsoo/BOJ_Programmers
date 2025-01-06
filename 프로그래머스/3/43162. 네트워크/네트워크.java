import java.util.*;

class Solution {
    static boolean[] visited;
    static int[][] computers;
    
    static void DFS(int x) {
        visited[x] = true;
        
        for (int i=0; i<computers[x].length; i++) {
            if (x!=i && !visited[i] && computers[x][i]==1) {
                DFS(i);
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        this.computers = computers;
        
        visited = new boolean[computers.length];
        
        for (int i=0; i<computers.length; i++) {
            if (!visited[i]) {
                DFS(i);
                answer++;
            }
        }
        
        return answer;
    }
}