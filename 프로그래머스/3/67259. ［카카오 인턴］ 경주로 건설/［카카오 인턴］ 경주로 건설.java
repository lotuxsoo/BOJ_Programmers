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
    
    static int BFS(int[][] board) {
        int N = board.length;
        int M = board[0].length;
        int[][][] visited = new int[N][M][4];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        
        // 상(0), 하(1), 좌(2), 우(3)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, -1, 0)); // 초기: 방향없음(-1)

        int minCost = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y, dir = cur.dir, cost = cur.cost;
            
            if (x == N-1 && y == N-1) {
                minCost = Math.min(minCost, cost);
                continue;
            }
            
            for (int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) continue;
                if (board[nx][ny] == 1) continue;
                
                int newCost = (dir == i || dir == -1) ? cost + 100 : cost + 600;
                
                if (newCost < visited[nx][ny][i]) {
                    visited[nx][ny][i] = newCost;
                    queue.add(new Node(nx, ny, i, newCost));
                }
            }
        }
        
        return minCost;
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        
        answer = BFS(board);
        
        return answer;
    }
}