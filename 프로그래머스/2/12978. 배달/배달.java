import java.util.*;

class Solution {
    static class Node {
        int e, v;
        Node(int e, int v) {
            this.e = e;
            this.v = v;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        ArrayList<Node>[] A = new ArrayList[N+1]; // 1~N 사용
        for (int i=0; i<N+1; i++) {
            A[i] = new ArrayList<>();
        }
        
        // 인접리스트 양방향
        for (int i=0; i<road.length; i++) {
            int s = road[i][0];
            int e = road[i][1];
            int v = road[i][2];
            A[s].add(new Node(e, v));
            A[e].add(new Node(s, v));
        }
        
        // 거리 배열 초기화
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.v, b.v));
        pq.add(new Node(1, 0));
        
        // 다익스트라 탐색
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            for (Node next : A[cur.e]) {
                if (dist[next.e] > cur.v + next.v) {
                    dist[next.e] = cur.v + next.v;
                    pq.add(new Node(next.e, dist[next.e]));
                }
            }
        }
        
        for (int i=1; i<N+1; i++) {
            if (dist[i] <= K) answer++;
        }

        return answer;
    }
}