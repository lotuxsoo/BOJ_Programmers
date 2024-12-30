import java.io.*;
import java.util.*;

class Solution {
    static int n;
    static int m;
    
    static boolean cango(int x, int y, int[][] maps) {
        if (!(0 <= x && x < n && 0 <= y && y < m)) return false;
        if (maps[x][y] == 0) return false;
        return true;
    }
    
    static int BFS(int[][] maps) {
        Queue<int[]> que = new ArrayDeque<>();
        n = maps.length;
        m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        
        que.add(new int[]{0,0});
        visited[0][0] = true;
        int ans = 1;
        
        while (!que.isEmpty()) {
            
            int size = que.size();
            for (int t=0; t<size; t++) {
                int[] now = que.poll();
                
                if (now[0]==n-1 && now[1]==m-1) {
                    return ans;
                }
                
                int[] dx = {-1,1,0,0};
                int[] dy = {0,0,-1,1};

                for (int i=0; i<4; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
                    if (cango(nx,ny,maps) && !visited[nx][ny]) {
                        que.add(new int[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            ans++;
        }

        return -1;
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        answer = BFS(maps);
        
        return answer;
    }
}