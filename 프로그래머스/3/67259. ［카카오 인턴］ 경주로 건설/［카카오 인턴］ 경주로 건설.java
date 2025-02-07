import java.util.*;

class Solution {
    static class State {
        int x, y, pos;
        State(int x, int y, int pos) {
            this.x = x;
            this.y = y;
            this.pos = pos;
        }
    }
    
    static int[][][] dp;
    static int N;
    static final int INF = 1_000_000_000;
    
    // 세로방향:0,2 가로방향:1,3
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    
    public int solution(int[][] board) {
        int answer = INF;
        N = board.length;
        
        // dp[x][y][pos]: 현재까지의 최소 비용
        dp = new int[N][N][2]; // 0:세로, 1:가로
        
        // INF로 초기화
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        
        // 초기값 설정
        dp[0][0][0] = 0;
        dp[0][0][1] = 0;
        
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0,0,0));
        queue.add(new State(0,0,1));
        
        while (!queue.isEmpty()) {
            State cur = queue.poll();
            int x=cur.x, y=cur.y, pos=cur.pos;
            
            if (x==N-1 && y==N-1) {
                answer = Math.min(answer, dp[x][y][pos]);
                continue;
            }
            
            if (dp[x][y][pos] == INF) continue;
            
            // 세로방향:0,2 가로방향:1,3
            for (int i=0; i<4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if (!(0<=nx&&nx<N&&0<=ny&&ny<N)) continue;
                
                // 이동 가능한 경로 탐색
                if (board[nx][ny] == 0) {
                    if (((i%2==0) && (pos==0)) || ((i%2==1) && (pos==1))) {
                        if (dp[nx][ny][pos] > dp[x][y][pos] + 100) {
                            dp[nx][ny][pos] = dp[x][y][pos] + 100;
                            queue.add(new State(nx,ny,pos));
                        }
                    } else {
                        if (dp[nx][ny][Math.abs(1-pos)] > dp[x][y][pos] + 600) {
                            dp[nx][ny][Math.abs(1-pos)] = dp[x][y][pos] + 600;
                            queue.add(new State(nx,ny,Math.abs(1-pos)));
                        }
                    } 
                }
            }
        }
        
        
        return answer;
    }
}