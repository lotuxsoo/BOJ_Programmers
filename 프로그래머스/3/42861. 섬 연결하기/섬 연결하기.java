import java.util.*;

class Solution {
    static int[] parent;
    
    static int find(int x) {
        if (x == parent[x]) return x;
        parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        parent[root1] = root2;
    }
    
    static boolean isFinish() {
        int x = parent[0];
        for (int i=1; i<parent.length; i++) {
            if (x != parent[i]) return false;
            x = parent[i];
        }
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        Arrays.sort(costs, (o1,o2) -> Integer.compare(o1[2],o2[2]));
        
        parent = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        for (int[] edge : costs) {
            // 경로 연결
            if (isFinish()) break;
            if (find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                answer += edge[2];
            }

        }
        
        return answer;
    }
}