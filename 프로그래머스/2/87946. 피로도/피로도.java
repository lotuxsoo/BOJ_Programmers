import java.util.*;


class Solution {
    static int n;
    static boolean[] visited;
    static int MAX_VAL = Integer.MIN_VALUE;
    
    static void DFS(int x, ArrayList<Integer> list, int k, int[][] dungeons) {

        MAX_VAL = Math.max(MAX_VAL, list.size());
        
        for (int i=0; i<n; i++) {
            if (visited[i]) continue;
            
            int a = dungeons[i][0]; // 최소 필요 피로도
            int b = dungeons[i][1]; // 소모 피로도
            
            if (k >= a) {
                visited[i] = true;
                ArrayList<Integer> newlist = new ArrayList<>(list);
                newlist.add(i);
                DFS(i, newlist, k - b, dungeons);
                visited[i] = false;
            }
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        n = dungeons.length;
        visited = new boolean[n];
        
        for (int i=0; i<n; i++) {
            DFS(i, new ArrayList<>(), k, dungeons);
        }
        
        answer = MAX_VAL;
        
        return answer;
    }
}