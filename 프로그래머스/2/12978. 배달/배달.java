import java.util.*;

class Solution {
    static class Node {
        int end, cost;
        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    
    static ArrayList<Node>[] A;
    
    static int BFS(int N, int K) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost,b.cost));
        pq.add(new Node(1, 0));
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            for (Node next : A[cur.end]) {
                if (dist[next.end] > cur.cost + next.cost) {
                    dist[next.end] = cur.cost + next.cost;
                    pq.add(new Node(next.end, dist[next.end]));
                }
            } 
        }
        
        int cnt = 0;
        for (int i=1; i<N+1; i++) {
            if (dist[i] <= K) {
                cnt++;
            }
        }
        
        return cnt;
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        A = new ArrayList[N+1]; // 마을 번호: 1~N
        for (int i=0; i<N+1; i++) {
            A[i] = new ArrayList<>();
        }
        
        for (int[] row : road) {
            A[row[0]].add(new Node(row[1], row[2]));
            A[row[1]].add(new Node(row[0], row[2]));
        }
        
        answer = BFS(N, K);
        
        return answer;
    }
}