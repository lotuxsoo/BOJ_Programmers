import java.util.*;

class Solution {
    
    static void dfs(int now, Set<Integer> set, int sheep, int wolf, int[] info) {
        
        if (sheep <= wolf) return;
        
        ans = Math.max(ans, sheep);
        visited[now] = true;
        
        for (int index : set) { // 후보들 중 하나를 골라            
            for (int next : nodes[index]) { // 다음 후보들을 탐색
                if (visited[next]) continue;
                
                Set<Integer> newSet = new HashSet<>(set);
                
                if (info[next] == 0) {
                    newSet.add(next);
                    dfs(next, newSet, sheep+1, wolf, info);
                } else if ((info[next] == 1) && wolf+1 < sheep) {
                    newSet.add(next);
                    dfs(next, newSet, sheep, wolf+1, info);
                }
            }
        }
        
        visited[now] = false;
    }

    static ArrayList<Integer>[] nodes;
    static int N;
    static boolean[] visited;
    static int ans = 0;
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;    
        nodes = new ArrayList[N];
        
        for (int i=0; i<N; i++) {
            nodes[i] = new ArrayList<>();
        }
        
        // 트리 구성 (양방향 연결, 문제에 따라 다르게 구성 가능)
        for (int[] edge : edges) {
            nodes[edge[0]].add(edge[1]);
            nodes[edge[1]].add(edge[0]);
        }
        
        visited = new boolean[N];
        visited[0] = true;
        dfs(0, new HashSet<>(Set.of(0)), 1, 0, info);
        
        return ans;
    }
}