import java.util.*;

class Solution {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n,m;
    static int[][] copys;
    static int[][] distance;
    static Queue<Point> que = new ArrayDeque<>();
    
    static void BFS() {
        distance[0][0] = 1;
        que.add(new Point(0,0));
        
        while (!que.isEmpty()) {
            Point p = que.poll();
            int x = p.x, y = p.y;
            
            for (int i=0; i<4; i++) {
                int[] dx = {-1,1,0,0};
                int[] dy = {0,0,-1,1};
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (canGo(newX, newY)) {
                    distance[newX][newY] = distance[x][y] + 1;
                    que.add(new Point(newX,newY));
                }
            }
        }
    }
    
    static boolean canGo(int x, int y) {
        if (!(0 <= x && x < n && 0 <= y && y < m)) return false;
        if (distance[x][y] != -1 || copys[x][y] == 0) return false;
        return true;
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length; // 세로
        m = maps[0].length; // 가로
        copys = new int[n][m];
        for (int i=0; i<n; i++) {
            copys[i] = Arrays.copyOf(maps[i], maps[i].length);
        }
        distance = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(distance[i], -1);
        }
        
        BFS();
        
        if (distance[n-1][m-1] == -1) return -1;
        else answer = distance[n-1][m-1];
        
        return answer;
    }
}