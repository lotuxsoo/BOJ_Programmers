import java.util.*;

class Solution {
    
    static int[] parent, rank;
    
    static void union(int x, int y) {
        int root1 = find(x), root2 = find(y);
        if (root1 != root2) {
            if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }
        }
    }
    
    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        parent = new int[n+1];
        rank = new int[n+1];
        for (int i=0; i<n+1; i++) {
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
            if (i == parent[i]) {
                answer++;
            }
        }
        
        return answer;
    }
}