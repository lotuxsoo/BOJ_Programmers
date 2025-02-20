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

    static int calculateCost(int prevDir, int dir, int cost) {
        int newCost = cost;
        if (prevDir == -1 || (prevDir == dir)) {
            newCost += 100;
        } else {
            newCost += 600;
        }
        return newCost;
    }
    
    static int[] dx = {-1,0,1,0}; // 상(0),좌(1),하(2),우(3)
    static int[] dy = {0,-1,0,1};
    static int[][][] dp;
    static int N;
    
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        
        // (x,y)에서의 최소비용 저장
        dp = new int[N][N][2];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);   
            }
        }
        
        // 시작점 초기화 필수
        dp[0][0][0] = 0;
        dp[0][0][1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost,b.cost));
        pq.add(new Node(0, 0, -1, 0));
        pq.add(new Node(0, 0, -1, 1));
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (now.x == N-1 && now.y == N-1) {
                return dp[N-1][N-1][now.dir];
            }
            
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (!(0<=nx && nx<N && 0<=ny && ny<N)) continue;
                if (board[nx][ny] == 1) continue;
                
                int newCost = calculateCost(now.dir, i%2, now.cost);
                if (dp[nx][ny][i%2] == Integer.MAX_VALUE || dp[nx][ny][i%2] > newCost) {
                    dp[nx][ny][i%2] = newCost;
                    pq.add(new Node(nx, ny, i%2, newCost));
                }
            }
        }
        
        return -1;
    }
}