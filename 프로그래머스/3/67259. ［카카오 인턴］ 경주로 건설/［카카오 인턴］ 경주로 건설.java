import java.util.*;

class Solution {
    static class Node {
        int x, y, dir, cost;
        Node(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    static int N;
    static int[][][] dist;
    
    static int BFS(int[][] board) { 
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, -1, 0)); // 초기: -1(방향없음)
        
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        int minCost = Integer.MAX_VALUE;
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y, dir = cur.dir, cost = cur.cost;
            
            if (x == N-1 && y == N-1) {
                // 이 좌표에 도달한 모든 방향의 비용 중 최솟값을 선택
                minCost = Math.min(minCost, cost);
                continue;
            }
            
            // 0:상 1:하 2:좌 3:우
            for (int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) continue;
                if (board[nx][ny] == 1) continue;
                
                if (dir == i || dir == -1) {
                    int newCost = cost + 100;
                    if (dist[nx][ny][i] > newCost) {
                        dist[nx][ny][i] = newCost;
                        queue.add(new Node(nx, ny, i, dist[nx][ny][i]));
                    }
                } else {
                    int newCost = cost + 600;
                    if (dist[nx][ny][i] > newCost) {
                        dist[nx][ny][i] = newCost;
                        queue.add(new Node(nx, ny, i, dist[nx][ny][i]));
                    }
                }
            }
        }
        
        return minCost;
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        
        N = board.length;
        dist = new int[N][N][4];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);   
            }
        }
        
        answer = BFS(board);

        return answer;
    }
}