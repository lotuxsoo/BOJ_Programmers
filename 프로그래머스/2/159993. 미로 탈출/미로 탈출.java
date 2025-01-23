import java.util.*;

class Solution {
    static class Node {
        int x, y;
        char type;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        Node(int x, int y, char type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
    
    static Node S;
    static Node L;
    static Node E;
    static int INF = Integer.MAX_VALUE;

    public int solution(String[] maps) {
        int answer = 0;
        
        int n = maps.length;
        int m = maps[0].length();
        char[][] chMap = new char[n][m];
        
        // String[] -> char[][]
        for (int i=0; i<n; i++) {
            char[] ch = maps[i].toCharArray();
            for (int j=0; j<ch.length; j++) {
                chMap[i][j] = ch[j];
                if (chMap[i][j] == 'S') {
                    S = new Node(i, j, 'S');
                } else if (chMap[i][j] == 'E') {
                    E = new Node(i, j, 'E');
                } else if (chMap[i][j] == 'L') {
                    L = new Node(i, j, 'L');
                }
            }
        }
    
        // 최단거리 저장할 int[][]
        int[][] dist = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[S.x][S.y] = 0;
    
        Queue<Node> Q = new LinkedList<>();
        Q.add(S);
            
        boolean found = false;
        
        // S -> L
        while (!Q.isEmpty()) {
            Node cur = Q.poll();
            
            if (cur.x == L.x && cur.y == L.y) {
                found = true;
                break;
            }
            
            int[] dx = {-1,1,0,0};
            int[] dy = {0,0,-1,1};
            for (int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(0 <= nx && nx < n && 0 <= ny && ny < m)) continue;
                if (chMap[nx][ny] == 'X') continue;
                if (dist[nx][ny] != INF) continue;
                
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                Q.add(new Node(nx, ny));
            }
        }
        
        if (!found) return -1;
        answer += dist[L.x][L.y];
        
        dist = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[L.x][L.y] = 0;
        
        Q = new LinkedList<>();
        Q.add(L);
        
        // L -> E
        while (!Q.isEmpty()) {
            Node cur = Q.poll();
            
            if (cur.x == E.x && cur.y == E.y) {
                return answer += dist[E.x][E.y];
            }
            
            int[] dx = {-1,1,0,0};
            int[] dy = {0,0,-1,1};
            for (int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(0 <= nx && nx < n && 0 <= ny && ny < m)) continue;
                if (chMap[nx][ny] == 'X') continue;
                if (dist[nx][ny] != INF) continue;
                
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                Q.add(new Node(nx, ny));
            }
        }
        
        return -1;
    }
}