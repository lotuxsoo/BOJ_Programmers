class Solution {
    static int[] parent;
    
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
            parent[root1] = root2;
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        parent = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
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