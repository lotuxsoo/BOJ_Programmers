import java.util.*;

class Solution {
    static int[][] maps;
    static int n,m;
    static int[][] distance;
    
    static boolean cango(int x, int y) {
        if (!(0 <= x && x < n && 0 <= y && y < m)) return false;
        if (distance[x][y] != -1) return false;
        if (maps[x][y] == 0) return false;
        return true;
    }
    
    static int BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        distance[x][y] = 1;
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == n-1 && now[1] == m-1) {
                return distance[n-1][m-1];
            }
            int[] dx = {-1,1,0,0};
            int[] dy = {0,0,-1,1};
            for (int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (cango(nx, ny)) {
                    queue.add(new int[]{nx, ny});
                    distance[nx][ny] = distance[now[0]][now[1]] + 1;
                }
            }
        }
        
        return -1;
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        this.maps = maps;
        n = maps.length;
        m = maps[0].length;
        
        distance = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(distance[i],-1);
        }
            
        answer = BFS(0,0);
        
        return answer;
    }
}