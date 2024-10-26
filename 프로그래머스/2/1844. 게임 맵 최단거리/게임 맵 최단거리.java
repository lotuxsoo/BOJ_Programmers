import java.util.*;

class Solution {
    // 동,서,남,북
    static int[] dx = {0,0,1,-1}; // 수직 이동
    static int[] dy = {1,-1,0,0}; // 수평 이동
    static int n,m; // 세로,가로
    static boolean[][] visited;
    
    // 좌표와 거리를 저장하는 Node 클래스
    static class Node {
        int x, y, distance;
        Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    
    static boolean canGo(int x, int y) {
        return 0 <= x && x <= n-1 && 0 <= y && y <= m-1;
    }
    
    static int BFS(int x, int y, int[][] maps) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(x,y,1));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            if (current.x==n-1 && current.y==m-1) return current.distance;
            
            for (int i=0; i<4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];
                
                if (canGo(newX, newY) && maps[newX][newY] == 1 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new Node(newX,newY, current.distance+1));
                }
            }
        }
        return -1;
    }
    
    public int solution(int[][] maps) {
        n = maps.length; // 세로 길이
        m = maps[0].length; // 가로 길이
        visited = new boolean[n][m];
        
        return BFS(0,0,maps);
    }
}