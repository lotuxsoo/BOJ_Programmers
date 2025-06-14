import java.util.*;

class Solution {
    static class Node {
        int x, y, dir, cost;
        Node (int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static final int INF = 1_000_000_000;
    
    public int solution(int[][] board) {
        int answer = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        // 가로방향:0, 세로방향:1
        pq.add(new Node(0,0,0,0));
        pq.add(new Node(0,0,1,0));
        
        int N = board.length;
        int M = board[0].length;
        int[][][] dp = new int[N][M][2];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                Arrays.fill(dp[i][j], INF);   
            }
        }
        dp[0][0][0] = 0;     
        dp[0][0][1] = 0;      
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (cur.cost > dp[cur.x][cur.y][cur.dir]) continue;
            
            if (cur.x == N-1 && cur.y == N-1) {
                break;
            }
            
            for (int i=0; i<2; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >=0 && nx < N && ny >= 0 && nx < M && board[nx][ny] == 0) {
                    if (cur.dir == 0) {
                        if (dp[nx][ny][1] > dp[cur.x][cur.y][0] + 100 + 500) {
                            dp[nx][ny][1] = dp[cur.x][cur.y][0] + 100 + 500;
                            pq.add(new Node(nx,ny,1,dp[nx][ny][1]));
                        }
                    } else {
                        if (dp[nx][ny][1] > dp[cur.x][cur.y][1] + 100) {
                            dp[nx][ny][1] = dp[cur.x][cur.y][1] + 100;
                            pq.add(new Node(nx,ny,1,dp[nx][ny][1]));
                        }
                    }            
                }       
            }
            
            for (int i=2; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >=0 && nx < N && ny >= 0 && ny < M && board[nx][ny] == 0) {
                    if (cur.dir == 0) {
                        if (dp[nx][ny][0] > dp[cur.x][cur.y][0] + 100) {
                            dp[nx][ny][0] = dp[cur.x][cur.y][0] + 100;
                            pq.add(new Node(nx,ny,0,dp[nx][ny][0]));
                        }
                    } else {
                        if (dp[nx][ny][0] > dp[cur.x][cur.y][1] + 100 + 500) {
                            dp[nx][ny][0] = dp[cur.x][cur.y][1] + 100 + 500;
                            pq.add(new Node(nx,ny,0,dp[nx][ny][0]));
                        }
                    }         
                }     
            }
        }
        
        answer = Math.min(dp[N-1][N-1][0], dp[N-1][N-1][1]);
        
        return answer;
    }
}