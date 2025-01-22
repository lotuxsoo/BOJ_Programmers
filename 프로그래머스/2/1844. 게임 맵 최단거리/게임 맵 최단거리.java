import java.util.*;

class Solution {
    static int n;
    static int m;
 
    static boolean BFS(int[][] maps, int[][] dist) {
        boolean found = false;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        dist[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == n-1 && cur[1] == m-1) {
                found = true;
                break;
            }
            
            int[] dx = {-1,1,0,0};
            int[] dy = {0,0,-1,1};
            for (int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if ((0 <= nx && nx < n && 0 <= ny && ny < m) && maps[nx][ny] == 1) {
                    if (dist[nx][ny] > dist[cur[0]][cur[1]] + 1) {
                        dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }  
        }
        
        return found;
    }
    
    public int solution(int[][] maps) {
        int answer = 0;

        n = maps.length;
        m = maps[0].length;
        
        int[][] dist = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        if (!BFS(maps, dist)) return -1;
        
        answer = dist[n-1][m-1];
        
        return answer;
    }
}