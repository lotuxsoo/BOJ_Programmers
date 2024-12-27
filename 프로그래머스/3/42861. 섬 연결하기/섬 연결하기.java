import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int x,y,cost;
        Node(int x,int y,int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) { // 거리 최소 순대로
            return this.cost - o.cost;
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
            parent[root2] = root1;
        }
    }
    
    static int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        ArrayList<Node> nodes = new ArrayList<>();
        for (int[] arr : costs) {
            int a = arr[0];
            int b = arr[1];
            int cost = arr[2];
            nodes.add(new Node(a,b,cost));
        }
        Collections.sort(nodes);
        
        // 노드 초기화
        parent = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        int cnt = 0;
        
        for (Node node : nodes) {
            if (find(node.x) != find(node.y)) {
                union(node.x, node.y);
                answer += node.cost;
                cnt++;
                if (cnt == n-1) break;
            }
        }
        
        return answer;
    }
}