import java.util.*;

class Solution {
    
    static char[][] map;
    static int[] start;
    static int[] levar;
    static int[] end;
    static int N,M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] dist;
    static final int INF = 1_000_000_000;
    
    public int solution(String[] maps) {
        int answer = 0;
        
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];
        
        for (int i=0; i<maps.length; i++) {
            char[] ch = maps[i].toCharArray();
            for (int j=0; j<maps[i].length(); j++) {
                if (ch[j] == 'S') {
                    start = new int[]{i,j};
                } else if (ch[j] == 'E') {
                    end = new int[]{i,j};
                } else if (ch[j] == 'L') {
                    levar = new int[]{i,j};
                }
                map[i][j] = ch[j];
            }
        }

        // BFS: S->L 
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1]});
        boolean found = false;
        
        dist = new int[N][M];
        for (int i=0; i<N; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[start[0]][start[1]] = 0;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x=cur[0], y=cur[1];
            
            if (x==levar[0] && y==levar[1]) {
                answer += dist[x][y];
                found = true;
                break;
            }
            
            for (int i=0; i<4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if (!(0<=nx&&nx<N&&0<=ny&&ny<M)) continue;
                if (map[nx][ny] == 'X') continue;
                
                if (dist[nx][ny] > dist[x][y] + 1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(new int[]{nx,ny});
                }
            }
        }
        
        if (!found) return -1;
        
        dist = new int[N][M];
        for (int i=0; i<N; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[levar[0]][levar[1]] = 0;
        
        // BFS: L->E
        queue = new LinkedList<>();
        queue.add(new int[]{levar[0], levar[1]});
        found = false;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x=cur[0], y=cur[1];
            
            if (x==end[0] && y==end[1]) {
                answer += dist[x][y];
                found = true;
                break;
            }
            
            for (int i=0; i<4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if (!(0<=nx&&nx<N&&0<=ny&&ny<M)) continue;
                if (map[nx][ny] == 'X') continue;
                
                if (dist[nx][ny] > dist[x][y]+1) {
                    dist[nx][ny] = dist[x][y]+1;
                    queue.add(new int[]{nx,ny});
                }
            }
        }
        
        if (!found) return -1;
        
        return answer;
    }
}