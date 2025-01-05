import java.util.*;

class Solution {
    static int[][] wires;
    static int n;
    static int[][] adj;
    static boolean[] visited;
    
    static int DFS(int x) { // DFS(1,1)
        int cnt = 1;
        visited[x] = true;
        
        for (int i=1; i<=n; i++) {
            if (!visited[i] && adj[x][i] == 1) {
                visited[i] = true;
                cnt += DFS(i); // DFS(2), DFS(3), DFS(4)
            }
        }
        
        return cnt;
    }
    
    static void backtrack(int x) {
        // 인접리스트 초기화
        adj = new int[n+1][n+1];
        
        for (int i=0; i<wires.length; i++) {
            if (i == x) continue;
            int a = wires[i][0];
            int b = wires[i][1];
            adj[a][b] = 1;
            adj[b][a] = 1;
        }
    }
    
    public int solution(int n, int[][] wires) {
        int answer = -1;
        this.n = n;
        this.wires = wires;
        int MIN_VAL = Integer.MAX_VALUE;
        
        // 전선들 중 하나를 고르는 백트래킹
        for (int i=0; i<wires.length; i++) {
            backtrack(i);
            
            visited = new boolean[n+1];
            int cnt = DFS(1); // 정점 1부터 탐색
            
            int remain = n - cnt;
            MIN_VAL = Math.min(MIN_VAL, Math.abs(cnt - remain));
        }
        
        answer = MIN_VAL;
        
        return answer;
    }
}