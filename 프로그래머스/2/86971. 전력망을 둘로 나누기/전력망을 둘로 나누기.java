import java.util.*;

class Solution {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int MIN_VAL = Integer.MAX_VALUE;
    
    static int DFS(int cnt, int x) {
        visited[x] = true;
        for (int next : A[x]) {
            if (!visited[next]) {
                cnt = DFS(cnt+1, next);
            }
        }
        return cnt;
    }
    
    public int solution(int n, int[][] wires) {
        int answer = -1;
        
        A = new ArrayList[n+1];
        for (int i=0; i<n+1; i++) {
            A[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            A[wire[0]].add(wire[1]);
            A[wire[1]].add(wire[0]);
        }
        
        for (int[] wire : wires) {
            A[wire[0]].remove(Integer.valueOf(wire[1]));
            A[wire[1]].remove(Integer.valueOf(wire[0]));
            
            visited = new boolean[n+1];
            int cnt = DFS(1, wire[0]);
            MIN_VAL = Math.min(MIN_VAL, Math.abs((n-cnt)-cnt));
            
            A[wire[0]].add(wire[1]);
            A[wire[1]].add(wire[0]);
        }
        
        answer = MIN_VAL;
        
        return answer;
    }
}