import java.util.*;

class Solution {
    static class Node {
        int x, y, z;
        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    
    static int BFS(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int[][] dist = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
 
        dist[0][0] = 1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, dist[0][0]));
        
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            if (cur.x == n-1 && cur.y == m-1) {
                return cur.z;
            }
            
            for (int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(0 <= nx && nx < n && 0 <= ny && ny < m)) continue;
                if (maps[nx][ny] == 0) continue;
                
                if (dist[nx][ny] > cur.z + 1) {
                    dist[nx][ny] = cur.z + 1;
                    queue.add(new Node(nx, ny, dist[nx][ny]));
                }
            }
        }
        
        return -1;
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        answer = BFS(maps);
        
        return answer;
    }
}