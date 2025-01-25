import java.util.*;

class Solution {
    static ArrayList<Integer>[] T;
    
    static int DFS(int x, int cnt, boolean[] visited) {
        visited[x] = true;
        
        for (int next : T[x]) {
            if (!visited[next]) {
                cnt = DFS(next, cnt+1, visited);
            }
        }
        
        return cnt;
    }
         
    public int solution(int n, int[][] wires) {
        int answer = -1;
        int MIN_VAL = Integer.MAX_VALUE;
        
        // ArrayList 배열 초기화
        T = new ArrayList[n+1];
        for (int i=0; i<n+1; i++) {
            T[i] = new ArrayList<>();
        }
        
        // 인접리스트 생성
        for (int i=0; i<wires.length; i++) {
            T[wires[i][0]].add(wires[i][1]);
            T[wires[i][1]].add(wires[i][0]);
        }
        
        // 간선 끊기
        for (int i=0; i<wires.length; i++) {
            T[wires[i][0]].remove(Integer.valueOf(wires[i][1]));
            T[wires[i][1]].remove(Integer.valueOf(wires[i][0]));
            
            boolean[] visited = new boolean[n+1];
            int subset = DFS(1, 1, visited);
            MIN_VAL = Math.min(MIN_VAL, Math.abs((n - subset) - subset));
            
            T[wires[i][0]].add(wires[i][1]);
            T[wires[i][1]].add(wires[i][0]);
        }
        
        answer = MIN_VAL;
        
        return answer;
    }
}