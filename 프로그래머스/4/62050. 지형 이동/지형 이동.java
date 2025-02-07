import java.util.*;

class Solution {
    static class Edge {
        int group1, group2, cost;
        Edge(int group1, int group2, int cost) {
            this.group1=group1;
            this.group2=group2;
            this.cost=cost;
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
    
    static void dfs(int x, int y, int groupNum, int[][] land, int height) {
        dist[x][y] = groupNum;
        
        for (int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (!(0<=nx&&nx<N&&0<=ny&&ny<M)) continue;
            if (dist[nx][ny] != -1) continue;
            
            int diff = Math.abs(land[x][y]-land[nx][ny]);
            
            if (diff <= height) {
                dfs(nx,ny,groupNum,land,height);
            }
        }
    }
    
    static int[][] dist;
    static int N, M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static PriorityQueue<Edge> edgeQ;
    static int[] parent;
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        
        // 맵에 그룹넘버 저장
        dist = new int[N][M];
        for (int i=0; i<N; i++) {
            Arrays.fill(dist[i], -1);
        }
        
        int groupCount = 0;
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (dist[i][j] == -1) {
                    groupCount++;
                    dfs(i,j,groupCount,land,height);
                }
            }
        }
    
        edgeQ = new PriorityQueue<>((a,b) -> Integer.compare(a.cost,b.cost));
        
        // 에지리스트 구하기 (모든 정점에 대해 4방향 탐색)
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                for (int k=0; k<4; k++) {
                    int nx = i+dx[k];
                    int ny = j+dy[k];
                    if (!(0<=nx&&nx<N&&0<=ny&&ny<M)) continue;
                    
                    int diff = Math.abs(land[i][j]-land[nx][ny]);
                    if (diff > height) {
                        edgeQ.add(new Edge(dist[i][j],dist[nx][ny],diff));
                    }
                }
            }
        }
        
        // 유니온파인드
        parent = new int[groupCount+1];
        for (int i=0; i<=groupCount; i++) {
            parent[i] = i;
        }
        
        // groupCount-1 만큼 간선 연결
        int count = 0;
        int totalCost = 0;
        while (!edgeQ.isEmpty() && count < groupCount-1) {
            Edge cur = edgeQ.poll();
            int group1=cur.group1, group2=cur.group2, cost=cur.cost;
            
            if (find(group1) != find(group2)) {
                union(group1, group2);
                totalCost += cost;
                count++;
            }
        }
        
        answer = totalCost;
        
        
        return answer;
    }
}