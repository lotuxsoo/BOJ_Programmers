import java.util.*;

class Solution {
    static class Node {
        int sheep, wolf;
        Set<Integer> nodes;
        Node(int sheep, int wolf, Set<Integer> nodes) {
            this.sheep = sheep;
            this.wolf = wolf;
            this.nodes = nodes;
        }
    }
    static int n;
    static ArrayList<Integer>[] graph;
    static int MAX_VAL = Integer.MIN_VALUE;
    
    static void BFS(int[] info) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 0, new HashSet<>(Set.of(0))));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int sheep = cur.sheep, wolf = cur.wolf;
            Set<Integer> nodes = cur.nodes;
            
            MAX_VAL = Math.max(MAX_VAL, sheep);
            
            for (int node : nodes) {
                for (int next : graph[node]) {
                    if (nodes.contains(next)) continue;
                    
                    if (info[next] == 0) {
                        Set<Integer> newSet = new HashSet<>(nodes);
                        newSet.add(next);
                        queue.add(new Node(sheep+1, wolf, newSet));
                   } else if ((info[next] == 1) && (wolf+1 < sheep)){
                        Set<Integer> newSet = new HashSet<>(nodes);
                        newSet.add(next);
                        queue.add(new Node(sheep, wolf+1, newSet));
                   }
                }
            }
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        
        n = info.length; // 노드 개수
        graph = new ArrayList[n]; // 0:루트(양)
        for (int i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i=0; i<n-1; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph[a].add(b);
            graph[b].add(a);
        }
        
        BFS(info);
        
        answer = MAX_VAL;
        
        return answer;
    }
}