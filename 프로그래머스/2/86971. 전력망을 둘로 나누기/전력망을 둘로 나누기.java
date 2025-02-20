import java.util.*;

class Solution {
    
    static int DFS(int x) {
        visited[x] = true;
        int cnt = 1;
        
        for (int next : graph[x]) {
            if (!visited[next]) {
                cnt += DFS(next);
            }
        }
        
        return cnt;
    }

    static ArrayList<Integer>[] graph;
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        int answer = -1;
        int m = wires.length;
        
        graph = new ArrayList[n+1];
        for (int i=0; i<n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i=0; i<m; i++) {
            graph[wires[i][0]].add(wires[i][1]);
            graph[wires[i][1]].add(wires[i][0]);
        }
        
        // 간선 하나씩 끊기
        for (int i=0; i<m; i++) {
            graph[wires[i][0]].remove(Integer.valueOf(wires[i][1]));
            graph[wires[i][1]].remove(Integer.valueOf(wires[i][0]));
            visited = new boolean[n+1];
            
            int cnt = DFS(wires[i][0]);
            min = Math.min(min, Math.abs((n-cnt)-cnt));
            
            graph[wires[i][0]].add(wires[i][1]);
            graph[wires[i][1]].add(wires[i][0]);
        }
        
        answer = min;
        
        return answer;
    }
}