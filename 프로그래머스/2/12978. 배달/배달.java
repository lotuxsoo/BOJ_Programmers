import java.util.*;

class Solution {
    
    static class Node {
        int to;
        long cost;
        Node (int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    static final long INF = 1_000_000_000_000_000_000L;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        ArrayList<Node>[] graph = new ArrayList[N+1];
        for (int i=0; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] arr : road) {
            graph[arr[0]].add(new Node(arr[1], arr[2]));
            graph[arr[1]].add(new Node(arr[0], arr[2]));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Long.compare(a.cost, b.cost));
        pq.add(new Node(1,0));
        
        long[] dp = new long[N+1];
        Arrays.fill(dp, INF);
        dp[1] = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (cur.cost > dp[cur.to]) continue;
            
            for (Node next : graph[cur.to]) {
                if (dp[next.to] > dp[cur.to] + next.cost) {
                    dp[next.to] = dp[cur.to] + next.cost;
                    pq.add(new Node(next.to, dp[next.to]));
                }
            }
        }
        
        for (int i=1; i<=N; i++) {
            if (dp[i] <= K) {
                answer++;
            }
        }
        

        return answer;
    }
}