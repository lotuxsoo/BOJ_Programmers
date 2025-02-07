import java.util.*;

class Solution {
    
    static int bfs(int[][] maps) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        
        // **초기화 필수**
        dist[0][0] = 1;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            
            if (x==N-1 && y==M-1) {
                return dist[x][y];
            }
            
            for (int i=0; i<4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if (!(0<=nx&&nx<N&&0<=ny&&ny<M)) continue;
                if (maps[nx][ny] == 0) continue;
                  
                if (dist[nx][ny] > dist[x][y] + 1) {
                    // 거리 갱신, 큐에 추가
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        
        return -1;
    }
    
    static int[][] dist;
    static int N,M;
    static final int INF = 1_000_000_000;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        N = maps.length;
        M = maps[0].length;
        
        dist = new int[N][M];
        for(int i=0; i<N; i++) {
            Arrays.fill(dist[i], INF);
        }
        
        answer = bfs(maps);

        return answer;
    }
}