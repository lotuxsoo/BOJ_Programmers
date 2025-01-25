import java.util.*;

class Solution {
    static ArrayList<Integer>[] T;
    static boolean[] visited;
    
    static int DFS(int x, int cnt) {
        visited[x] = true;
        
        for (int next : T[x]) {
            if (!visited[next]) {
                cnt = DFS(next, cnt+1);
            }
        }
        
        return cnt;
    }
         
    public int solution(int n, int[][] wires) {
        int answer = -1;
        int MIN_VAL = Integer.MAX_VALUE;
        
        for (int k=0; k<wires.length; k++) {
            T = new ArrayList[n+1];
            for (int i=0; i<n+1; i++) {
                T[i] = new ArrayList<>();
            }
            
            visited = new boolean[n+1];
            
            for (int i=0; i<wires.length; i++) {
                if (i == k) continue;
                T[wires[i][0]].add(wires[i][1]);
                T[wires[i][1]].add(wires[i][0]);
            }
            int subset = DFS(1, 1);
            MIN_VAL = Math.min(MIN_VAL, Math.abs((n - subset) - subset));
        }

        answer = MIN_VAL;
       
        return answer;
    }
}