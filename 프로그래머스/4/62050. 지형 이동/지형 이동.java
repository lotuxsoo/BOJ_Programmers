import java.util.*;

class Solution {
    static class Node {
        int x, y, cost;
        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    
    static boolean[][] visited;
    static PriorityQueue<Node> pq;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        
        pq = new PriorityQueue<>((a,b)->Integer.compare(a.cost,b.cost));
        pq.add(new Node(0, 0, 0)); // 시작정점, 비용
        
        visited = new boolean[land.length][land[0].length];
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true; // 현재 정점 MST에 추가
            answer += cur.cost;
            
            // 인접 4방향 탐색
            for (int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                if (!(0<=nx&&nx<land.length&&0<=ny&&ny<land[0].length)) continue;
                if (visited[nx][ny]) continue;
                
                int diff = Math.abs(land[cur.x][cur.y] - land[nx][ny]);
                if (diff > height) {
                    pq.add(new Node(nx, ny, diff));
                } else {
                    pq.add(new Node(nx, ny, 0));
                }
            }
        }
        
        
        return answer;
    }
}