import java.util.*;

class Solution {
    static class State {
        int index, sheep, wolf;
        Set<Integer> nodes;
        
        State(int index, int sheep, int wolf, Set<Integer> nodes) {
            this.index = index;
            this.sheep = sheep;
            this.wolf = wolf;
            this.nodes = nodes;
        }
    }
    
    static List<List<Integer>> adjList = new ArrayList<>();
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        
        int N = info.length;
        
        for (int i=0; i<N; i++) {
            adjList.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
                
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 1, 0, new HashSet<>(Set.of(0))));
        
        while (!queue.isEmpty()) {
            State cur = queue.poll();
            
            answer = Math.max(answer, cur.sheep);            
            
            for (int node : cur.nodes) { // 후보 노드 집합 탐색
                for (int next : adjList.get(node)) { // 후보 노드 연결관계 탐색
                    if (cur.nodes.contains(next)) continue;

                    if (info[next] == 0) {
                        Set<Integer> newSet = new HashSet<>(cur.nodes);
                        newSet.add(next);
                        queue.add(new State(next, cur.sheep+1, cur.wolf, newSet));
                    } else if ((info[next] == 1) && cur.wolf+1 < cur.sheep) {
                        Set<Integer> newSet = new HashSet<>(cur.nodes);
                        newSet.add(next);
                        queue.add(new State(next, cur.sheep, cur.wolf+1, newSet));
                    }
                } 
            }
        }
        
        return answer;
    }
}