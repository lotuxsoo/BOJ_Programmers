import java.util.*;

class Solution {
    static class Node {
        int i, j, cost;
        Node (int i,int j,int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        
        int n = land.length;
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        boolean[][] visited = new boolean[n][n];
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost,o2.cost));
        pq.add(new Node(0,0,0));
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (visited[now.i][now.j]) continue;
            
            visited[now.i][now.j] = true;
            answer += now.cost;
            
            for (int i=0; i<4; i++) {
                int nx = now.i + dx[i];
                int ny = now.j + dy[i];
                if (!(0 <= nx && nx < n && 0 <= ny && ny < n)) continue;
                
                int tempCost = Math.abs(land[now.i][now.j] - land[nx][ny]);
                int newCost = tempCost > height ? tempCost : 0;
                pq.add(new Node(nx, ny, newCost));
            }
        }
        
        return answer;
    }
}