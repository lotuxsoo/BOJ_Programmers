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
    
    static void union(int x, int y) {
        int r1 = find(x);
        int r2 = find(y);
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
        
        for (int i=0; i<computers.length; i++) {
            for (int j=0; j<computers[i].length; j++) {
                if (i!=j && computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
         // 네트워크 개수 계산
        Set<Integer> networks = new HashSet<>();
        for (int i = 0; i < n; i++) {
            networks.add(find(i)); // 모든 노드의 루트를 찾아 Set에 저장
        }
        answer = networks.size();
        
        return answer;
    }
}