import java.util.*;

class Solution {
    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        int n = maps.length;
        int m = maps[0].length;
        
        int[][] dist = new int[n][m];
        
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0,0));
        dist[0][0] = 1;
        
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            
            int[] dx = {-1,1,0,0};
            int[] dy = {0,0,-1,1};
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (!(0 <= nx && nx < n && 0 <= ny && ny < m)) continue;
                if (maps[nx][ny] == 0) continue;
                
                if (dist[nx][ny] == 0) {
                    dist[nx][ny] = dist[now.x][now.y] + 1;
                    queue.add(new Node(nx,ny));
                }   
            }
        }
        
        answer = dist[n-1][m-1] == 0 ? -1 : dist[n-1][m-1];
 
        return answer;
    }
}