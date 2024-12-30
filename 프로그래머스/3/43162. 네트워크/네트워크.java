import java.io.*;
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
        int r1 = parent[a];
        int r2 = parent[b];
        if (r1 != r2) {
            parent[r1] = r2;
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
        
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<n; i++) {
            set.add(find(i));
        }
        
        answer = set.size();
        
        return answer;
    }
}