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
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        graph = new ArrayList[n];
        for (int i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] cost : costs) {
            graph[cost[0]].add(new Node(cost[1], cost[2]));
            graph[cost[1]].add(new Node(cost[0], cost[2]));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        
        boolean[] visited = new boolean[n];
        pq.add(new Node(0, 0));
        
        int cnt = 0;
        
        while (!pq.isEmpty() && cnt < n) {
            Node cur = pq.poll();
            
            if (visited[cur.dest]) continue;
            visited[cur.dest] = true;
            cnt++;
            answer += cur.cost;
            
            for (Node next : graph[cur.dest]) {
                if (!visited[next.dest]) {
                    pq.add(new Node(next.dest, next.cost));
                }    
            }
        } 
        
        return answer;
    }
}