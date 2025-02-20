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
        if (prevDir==-1 || (prevDir-dir)%2 == 0) {
            newCost += 100;
        } else {
            newCost += 600;
        }
        return newCost;
    }
    
    static int[] dx = {-1,0,1,0}; // 상(0),좌(1),하(2),우(3)
    static int[] dy = {0,-1,0,1};
    static int[][][] cost;
    static int N;
    static int bestCost = Integer.MAX_VALUE;
    
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        cost = new int[N][N][4];

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, -1, 0));
        
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            
            if (now.x == N-1 && now.y == N-1) {
                bestCost = Math.min(bestCost, now.cost);
                continue;
            }
            
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (!(0<=nx && nx<N && 0<=ny && ny<N)) continue;
                if (board[nx][ny] == 1) continue;
                
                int newCost = calculateCost(now.dir, i, now.cost);
                if (cost[nx][ny][i] == 0 || cost[nx][ny][i] > newCost) {
                    cost[nx][ny][i] = newCost;
                    queue.add(new Node(nx, ny, i, newCost));
                }
            }
        }
        
        answer = bestCost;
        
        return answer;
    }
}