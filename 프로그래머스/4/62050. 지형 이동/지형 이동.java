import java.util.*;

class Solution {
    static class Edge {
        int a, b, cost;
        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
    
    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    static void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        
        if (root1 != root2) {
            parent[root1] = root2;
        }
    }

    static void DFS(int x, int y, int areaIdx, int[][] land, int height) {
        area[x][y] = areaIdx;
        
        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!(0<=nx&&nx<N&&0<=ny&&ny<M)) continue;
            if (area[nx][ny] != -1) continue;
            
            int diff = Math.abs(land[x][y] - land[nx][ny]);
            if (diff <= height) {
               DFS(nx, ny, areaIdx, land, height);        
            }
        }
    }
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] area;
    static int N,M; // 세로,가로
    static boolean[][] visited;
    static int[] parent;
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        
        N = land.length;
        M = land[0].length;
        area = new int[N][M];
        // area 초기화
        for (int i=0; i<N; i++) {
            Arrays.fill(area[i], -1);
        }
        
        int areaIdx = 0; // 다른 영역의 개수
            
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (area[i][j] == -1) {
                    DFS(i, j, areaIdx, land, height);
                    areaIdx++;
                }
            }
        }
        
        visited = new boolean[N][M];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost,b.cost));
        
        // 모든 (x,y)에 대해 4방향 탐색
        for (int x=0; x<N; x++) {
            for (int y=0; y<M; y++) {
                for (int k=0; k<4; k++) {
                    int nx = x+dx[k];
                    int ny = y+dy[k];
                    if (!(0<=nx&&nx<N&&0<=ny&&ny<M)) continue;
                    if (area[x][y] != area[nx][ny]) {
                        int diff = Math.abs(land[x][y] - land[nx][ny]);
                        pq.add(new Edge(area[x][y], area[nx][ny], diff));
                    }
                }
            }
        }
        
        // 유니온 파인드
        parent = new int[areaIdx+1];
        for (int i=1; i<=areaIdx; i++) {
            parent[i] = i;
        }
        
        int cnt = 0;
        while (!pq.isEmpty() && (cnt < areaIdx-1)) {
            Edge cur = pq.poll();
            
            if (find(cur.a) != find(cur.b)) {
                union(cur.a, cur.b);
                cnt++;
                answer += cur.cost;
            }
        }
        
        return answer;
    }
}