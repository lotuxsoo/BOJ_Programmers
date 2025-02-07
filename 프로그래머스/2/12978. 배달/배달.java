import java.util.*;

class Solution {
    static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    static List<Edge>[] graph;
    static final int INF = 1_000_000_000;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        graph = new ArrayList[N+1];
        for (int i=0; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 양방향 정점기준 에지리스트
        for (int[] row : road) {
            graph[row[0]].add(new Edge(row[1],row[2]));
            graph[row[1]].add(new Edge(row[0],row[2]));
        }
        
        int[] dist = new int[N+1];
        // INF 초기화
        Arrays.fill(dist, INF);
        
        // 시작점 초기화
        dist[1] = 0;
        
        // 다익스트라, 우선순위큐
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)->Integer.compare(a.cost,b.cost));
        
        // 시작정점:1, cost:0
        pq.add(new Edge(1,0));
        
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if (dist[cur.to] == INF) continue;
            
            for (Edge next : graph[cur.to]) {
                if (dist[next.to] > dist[cur.to] + next.cost) {
                    dist[next.to] = dist[cur.to] + next.cost;
                    pq.add(new Edge(next.to, dist[next.to]));     
                }
            }
        }
        
        for (int x : dist) {
            if (x <= K) answer++;
        }
        
        return answer;
    }
}