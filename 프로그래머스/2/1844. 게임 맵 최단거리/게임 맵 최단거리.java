import java.util.*;

class Solution {
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n,m;
    
    static int bfs(int[][] maps) {
        int answer = -1;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,1});
        
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if (cur[0] == n-1 && cur[1] == m-1) {
                answer = cur[2];
                break;
            }
            
            for (int i=0; i<4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                if (!(0<=nx&&nx<n && 0<=ny&&ny<m)) continue;
                if ((maps[nx][ny] == 1) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx,ny,cur[2]+1});
                }
            }
        }
    
        return answer;
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length;
        
        answer = bfs(maps);
        
        return answer;
    }
}