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
    
    static void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        
        if (root1 != root2) {
            if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else {
                parent[root1] = root2;
                rank[root2]++;
            }
        }
    }
    
    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    static int[] parent;
    static int[] rank;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)->Integer.compare(a.cost,b.cost));
        
        for (int[] line : costs) {
            pq.add(new Edge(line[0],line[1],line[2]));
        }
        
        int cnt = 0;
        
        parent = new int[n];
        rank = new int[n];
        
        for (int i=0; i<n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        
        while (!pq.isEmpty() && (cnt < n-1)) {
            Edge cur = pq.poll();
            
            if (find(cur.a)!=find(cur.b)) {
                union(cur.a,cur.b);
                cnt++;
                answer += cur.cost;
            }
        }
        
        return answer;
    }
}