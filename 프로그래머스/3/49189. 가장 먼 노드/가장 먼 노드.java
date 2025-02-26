import java.util.*;

class Solution {
    
    static ArrayList<Integer>[] graph;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        // 그래프 초기화 (양방향)
        graph = new ArrayList[n+1];
        
        for (int i=0; i<n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        // 다익스트라
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        
        int MAX = Integer.MIN_VALUE;
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int next : graph[cur]) {
                if (dist[next] > dist[cur]+1) {
                    queue.add(next);
                    dist[next] = dist[cur]+1;
                    MAX = Math.max(MAX, dist[next]);
                }
            }
        }
    
        for (int i=1; i<=n; i++) {
            if (dist[i] == MAX) {
                answer++;
            }
        }
        
        return answer;
    }
}