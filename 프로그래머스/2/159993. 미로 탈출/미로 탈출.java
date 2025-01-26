import java.util.*;

class Solution {
    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int N;
    static int M;
    static char[][] miro;
    
    static int BFS(Node s, Node e, boolean[][] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(s);
        
        int sec = 0;
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        while (!queue.isEmpty()) {
            int size = queue.size(); // 현재 레벨의 노드 수
            
            for (int i=0; i<size; i++) {
                Node cur = queue.poll();
                
                if (cur.x == e.x && cur.y == e.y) {
                    return sec;
                }
                
                for (int j=0; j<4; j++) {
                    int nx = cur.x + dx[j];
                    int ny = cur.y + dy[j];
                    if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) continue;
                    if (visited[nx][ny]) continue; // 큐에 넣기 전에 조건 확인
                    if (miro[nx][ny] == 'X') continue;
                    
                    visited[nx][ny] = true; // 방문 처리하고 큐에 넣음
                    queue.add(new Node(nx, ny));
                } 
            }
            
            sec++; // 현재 레벨의 모든 노드를 탐색한 뒤 sec++;
        }
        return -1;
    }
    
    public int solution(String[] maps) {
        int answer = 0;
        
        Node S = null;
        Node L = null;
        Node E = null;
        
        N = maps.length;
        M = maps[0].length();
        
        // String[] -> char[][]
        miro = new char[N][];
        for (int i=0; i<N; i++) {
            miro[i] = maps[i].toCharArray();
            for (int j=0; j<maps[i].length(); j++) {
                if (miro[i][j] == 'S') {
                    S = new Node(i,j);
                } else if (miro[i][j] == 'L') {
                    L = new Node(i,j);
                } else if (miro[i][j] == 'E') {
                    E = new Node(i,j);
                }
             }
        }
        
        // BFS: S -> L
        boolean[][] visited = new boolean[N][M];
        int sec1 = BFS(S, L, visited);
        if (sec1 == -1) return -1;
        
        visited = new boolean[N][M];
        int sec2 = BFS(L, E, visited);
        if (sec2 == -1) return -1;
        
        answer = sec1 + sec2;
        
        return answer;
    }
}