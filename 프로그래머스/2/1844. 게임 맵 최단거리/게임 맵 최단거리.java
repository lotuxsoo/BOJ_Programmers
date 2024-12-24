import java.util.*;

class Solution {
    static class Node {
        int x,y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int n,m;
    static Queue<Node> que = new ArrayDeque<>();
    static int[][] dist; // 최단거리를 저장할 배열 생성
    static int[][] grid; // 원본배열 copy
    
    static void BFS() {
        que.offer(new Node(0,0));
        dist[0][0] = 1;
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            int x = now.x, y = now.y;
            
            int[] dx = new int[]{-1,1,0,0};
            int[] dy = new int[]{0,0,-1,1};
            for (int i=0; i<4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (canGo(newX,newY)) {
                    if (dist[newX][newY] == -1) {
                        dist[newX][newY] = dist[x][y] + 1;
                        que.offer(new Node(newX,newY));
                    }
                }
            }
        }
    }
    
    static boolean canGo(int x, int y) {
        if (!(0 <= x && x < n && 0 <= y && y < m)) return false;
        if (grid[x][y] == 0) return false;
        return true;
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length;
        
        grid = new int[n][m];
        for (int i=0; i<n; i++) {
            grid[i] = Arrays.copyOf(maps[i], maps[i].length);
        }
        
        dist = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(dist[i], -1);
        }

        BFS();
        
        answer = dist[n-1][m-1];
        
        return answer;
    }
}