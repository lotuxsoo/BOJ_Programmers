import java.util.*;

class Solution {
    
    static void dfs(int depth, int k, Set<Integer> indexSet, int[][] dungeons) {
        ans = Math.max(ans, indexSet.size());
        
        for (int i=0; i<N; i++) {
            if (indexSet.contains(i)) continue;
            
            int[] cur = dungeons[i];
            
            if (k >= cur[0]) {
                Set<Integer> newSet = new HashSet<>(indexSet);
                newSet.add(i);
                dfs(depth+1, k - cur[1], newSet, dungeons);
            }
        }
    }
    
    static int N;
    static int ans = 0;
    
    public int solution(int k, int[][] dungeons) {        
        N = dungeons.length;

        Set<Integer> indexSet = new HashSet<>();
        
        dfs(0, k, indexSet, dungeons);
        
        return ans;
    }
}