import java.util.*;

class Solution {
    static int n;
    static int[][] adj;
    static boolean[] visited;
    static int MIN_DIFF = Integer.MAX_VALUE;
    
    static int DFS(int node) {
        int count = 1; // 현재 노드 포함
        visited[node] = true;
        
        for (int i = 1; i <= n; i++) {
            if (adj[node][i] == 1 && !visited[i]) {
                count += DFS(i); // 연결된 노드의 개수 누적
            }
        }
        return count;
    }
    
    static void buildAdjMatrix(int skipEdge, int[][] wires) {
        adj = new int[n + 1][n + 1]; // 인접 행렬 초기화
        for (int i = 0; i < wires.length; i++) {
            if (i == skipEdge) continue; // 끊은 전선 제외
            
            int a = wires[i][0];
            int b = wires[i][1];
            adj[a][b] = 1;
            adj[b][a] = 1;
        }
    }
    
    public int solution(int n, int[][] wires) {
        this.n = n;

        for (int i = 0; i < wires.length; i++) {
            // 인접 행렬 생성
            buildAdjMatrix(i, wires);
            
            // 방문 상태 초기화
            visited = new boolean[n + 1];
            
            // 한쪽 네트워크 크기 계산
            int networkSize = DFS(1); // 항상 1번 노드에서 탐색 시작
            int otherNetworkSize = n - networkSize; // 나머지 네트워크 크기 계산
            
            // 두 네트워크 크기 차이의 최솟값 갱신
            MIN_DIFF = Math.min(MIN_DIFF, Math.abs(networkSize - otherNetworkSize));
        }
        
        return MIN_DIFF;
    }
}