class Solution {
    static int[] parent;
    static int[] rank;
    
    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    static void union(int x, int y) {
        int root1 = parent[x];
        int root2 = parent[y];
        
        if (root1 != root2) {
            if (rank[root1] < rank[root2]) {
                parent[root1] = root2; // 더 작은 root1을 root2에 붙임
            } else if (rank[root1] > rank[root2]) {
                parent[root2] = root1; // 더 작은 root2를 root1에 붙임
            } else {
                parent[root1] = root2; // 아무거나 붙이고 rank++
                rank[root2]++;
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        parent = new int[n];
        rank = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (computers[i][j] == 1) {
                    if (find(i) != find(j)) {
                        union(i, j);
                    }
                }
            }
        }
        
        for (int i=0; i<n; i++) {
            if (i == parent[i]) answer++;    
        }
        
        return answer;
    }
    
}