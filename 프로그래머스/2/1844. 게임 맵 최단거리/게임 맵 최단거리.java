import java.util.*;

class Solution {
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] dp;
    static int n,m;
    static final int INF = 1_000_000_000;
    
    static int bfs(int[][] maps) {
        int answer = -1;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        dp[0][0] = 1; // 중요!
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if (cur[0] == n-1 && cur[1] == m-1) {
                answer = dp[n-1][m-1];
                break;
            }
            
            for (int i=0; i<4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                if (!(0<=nx&&nx<n && 0<=ny&&ny<m)) continue;
                if (maps[nx][ny] == 1) {
                    if (dp[nx][ny] > dp[cur[0]][cur[1]] + 1) {
                        dp[nx][ny] = dp[cur[0]][cur[1]] + 1;
                        queue.add(new int[]{nx,ny});
                    }
                }
            }
        }
    
        return answer;
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length;
        
        dp = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        answer = bfs(maps);
        
        return answer;
    }
}