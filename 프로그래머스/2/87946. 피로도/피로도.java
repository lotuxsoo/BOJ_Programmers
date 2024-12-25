import java.util.*;

class Solution {
    
    static int N;
    static boolean[] visited;
    static int max = 0;
    
    static void DFS(int k, int[][] dungeons, int step) {
        max = Math.max(max, step);
        
        for (int i=0; i<N; i++) {
            int[] dungeon = dungeons[i];
            if (!visited[i] && k >= dungeon[0]) {
                visited[i] = true;
                DFS(k-dungeon[1], dungeons, step+1);
                visited[i] = false;
            }
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        N = dungeons.length;
        visited = new boolean[N];
        int step = 0;
    
        DFS(k, dungeons, step);
        
        answer = max;
        
        return answer;
    }
}