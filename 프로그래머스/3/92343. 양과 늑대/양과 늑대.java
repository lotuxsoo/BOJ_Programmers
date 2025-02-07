import java.util.*;

class Solution {

    static void dfs(int node, int sheep, int wolf, Set<Integer> set, int[] info, int[][] edges) {
        if (sheep <= wolf) return; // 추가한 인접 노드가 조건에 안맞으면 리턴
        
        visited[node] = true;
        MAX = Math.max(MAX, sheep);
        
        for (int candidate : set) { // 방문할수 있는 노드
            
             for (int nextCandidate : graph[candidate]) { // 의 인접노드들 추가
                Set<Integer> newSet = new HashSet<>(set);
                 
                if (newSet.contains(nextCandidate)) continue;
                newSet.add(nextCandidate);
                 
                if (info[nextCandidate] == 0) { // 조건 확인하고, 그 노드로 이동
                    dfs(nextCandidate, sheep+1, wolf, newSet, info, edges);
                } else if ((info[nextCandidate] == 1) && wolf+1<sheep) {
                    dfs(nextCandidate, sheep, wolf+1, newSet, info, edges);
                }
             }
        }
        
        visited[node] = false;
    }
    
    static List<Integer>[] graph;
    static int N; // 정점개수
    static boolean[] visited;
    static int MAX = 0;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        N = info.length;
        
        graph = new ArrayList[N];
        for (int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        visited = new boolean[N];
        
        dfs(0, 1, 0, new HashSet<>(Set.of(0)), info, edges);
        
        answer = MAX;
        
        return answer;
    }
}