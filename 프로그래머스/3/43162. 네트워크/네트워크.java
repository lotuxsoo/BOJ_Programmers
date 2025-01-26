import java.util.*;

class Solution {
    static int[] parent;
    
    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    static void union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);
        if (root1 != root2) {
            parent[root1] = root2;
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        // 부모 초기화
        parent = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<n; i++) {
            set.add(find(i));
        }
        
        answer = set.size();
        
        return answer;
    }
}