import java.util.*;

class Solution {
    static class Node {
        int dest, cost;
        Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static ArrayList<Node>[] graph;
    static int[] dist;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
            
        // 인접리스트 초기화
        graph = new ArrayList[N+1];
        for (int i=0; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // (A마을-B마을,걸리는시간) 양방향 연결
        for (int[] row : road) {
            graph[row[0]].add(new Node(row[1], row[2]));
            graph[row[1]].add(new Node(row[0], row[2]));
        }
        
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Node(1, 0));
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (dist[now.dest] < now.cost) continue;
            
            for (Node next : graph[now.dest]) {
                
                if (dist[next.dest] > next.cost + now.cost) {
                    dist[next.dest] = next.cost + now.cost;
                    pq.add(new Node(next.dest, dist[next.dest]));
                }
            }   
        }
        
        for (int i=1; i<=N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}