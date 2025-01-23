import java.util.*;

class Solution {
    static ArrayList<Integer>[] A;
    
    static int DFS(int cnt, int node, boolean[] visited) {
        visited[node] = true;
        
        for (int next : A[node]) {
            if (!visited[next]) {
                cnt = DFS(cnt+1, next, visited);
            }
        }
        
        return cnt;
    }
    
    public int solution(int n, int[][] wires) {
        int answer = -1;
        int MIN_VAL = Integer.MAX_VALUE;
        
        // 인접리스트 초기화
        A = new ArrayList[n+1];
        for (int i=0; i<n+1; i++) {
            A[i] = new ArrayList<>();
        }
        
        // 노드 양방향 연결
        for (int[] wire : wires) {
            int node1 = wire[0];
            int node2 = wire[1];
            A[node1].add(node2);
            A[node2].add(node1);
        }
        
        // 간선 하나씩 끊어서 확인
        for (int[] wire : wires) {
            int node1 = wire[0];
            int node2 = wire[1];
            
            A[node1].remove(Integer.valueOf(node2));
            A[node2].remove(Integer.valueOf(node1));
            
            boolean[] visited = new boolean[n+1];
            
            int subtree = DFS(1, node1, visited);
            int size = Math.abs(subtree - (n - subtree));
            MIN_VAL = Math.min(MIN_VAL, size);
            
            A[node1].add(Integer.valueOf(node2));
            A[node2].add(Integer.valueOf(node1));
        }
        
        answer = MIN_VAL;
        
        return answer;
    }
}