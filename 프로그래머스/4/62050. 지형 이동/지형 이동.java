import java.util.*;

class Solution {
    static class Edge {
        int x, y, cost;
        Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static void BFS(int x, int y, int groupNum, int[][] land, int height) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        group[x][y] = groupNum;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();    
            int cx = cur[0], cy = cur[1];
            
            for (int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (!(0 <= nx && nx < n && 0 <= ny && ny < m)) continue;
                if (group[nx][ny] != 0) continue; // 방문 여부 체크
                
                int sub = Math.abs(land[cx][cy] - land[nx][ny]);
                if (sub <= height) {
                    group[nx][ny] = groupNum;
                    queue.add(new int[]{nx,ny});
                }
            }
        } 
    }
    
    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    static void union(int a, int b) {
        int r1 = find(a);
        int r2 = find(b);
        if (r1 != r2) {
            parent[r1] = r2;
        }
    }
    
    static int n,m;
    static int[][] group;
    static int[] parent;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static PriorityQueue<Edge> pq;
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        
        n = land.length;
        m = land[0].length;
        group = new int[n][m];
        int groupNum = 0;
        
        // BFS 돌면서 그룹 탐색
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (group[i][j] == 0) {
                    groupNum++;
                    BFS(i, j, groupNum, land, height); 
                }
            }
        }
        
        // 다른 그룹 탐색하고 PQ에 저장
        parent = new int[groupNum+1];
        for (int i=0; i<groupNum+1; i++) {
            parent[i] = i;
        }
        
        pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                for (int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (!(0 <= nx && nx < n && 0 <= ny && ny < m)) continue;
                    
                    if (group[i][j] != group[nx][ny]) {
                        pq.add(new Edge(group[i][j], group[nx][ny], Math.abs(land[i][j] - land[nx][ny])));
                    }
                }
            }
        }
        
        // MST 구축
        int cnt = 0;
        while (cnt < groupNum-1) {
            Edge cur = pq.poll();
            
            if (find(cur.x) != find(cur.y)) {
                union(cur.x, cur.y);
                answer += cur.cost;
                cnt++;
            }
        }
        
        return answer;
    }
}