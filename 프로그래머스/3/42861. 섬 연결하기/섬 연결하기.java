import java.util.*;

class Solution {   
    
    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    static void union(int a, int b) {
        int p1 = find(a), p2 = find(b);
        if (p1 != p2) {
            parent[p1] = p2;
        }
    }
    
    static int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        List<int[]> edgeList = new ArrayList<>();
        
        for (int[] cost : costs) {
            edgeList.add(cost);
        }
        
        Collections.sort(edgeList, (a,b) -> Integer.compare(a[2], b[2]));
        
        int edgeCount = 0;
        
        for (int[] edge : edgeList) {
            if (find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                edgeCount++;
                answer += edge[2];
            }
            
            if (edgeCount == n) break;
        }
        
        return answer;
    }
}