import java.util.*;

class Solution {
    static class Node {
        int idx, sheep, wolf;
        Set<Integer> nodes;
        Node(int idx, int sheep, int wolf, Set<Integer> nodes) {
            this.idx = idx;
            this.sheep = sheep;
            this.wolf = wolf;
            this.nodes = nodes;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        
        int n = info.length;
        ArrayList<Integer>[] A = new ArrayList[n];
        for (int i=0; i<n; i++) {
            A[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            A[edge[0]].add(edge[1]);
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 1, 0, new HashSet<>(Set.of(0))));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            answer = Math.max(answer, cur.sheep);
            
            for (int node : cur.nodes) {
                for (int next : A[node]) {
                    if (cur.nodes.contains(next)) continue;
                    
                    int nextSheep = (info[next] == 0) ? cur.sheep + 1 : cur.sheep;
                    int nextWolf = (info[next] == 1) ? cur.wolf + 1 : cur.wolf;

                    if (nextSheep > nextWolf) {
                        Set<Integer> newSet = new HashSet<>(cur.nodes);
                        newSet.add(next);
                        queue.add(new Node(node, nextSheep, nextWolf, newSet));
                    }
                }
            } 
        }
        
        return answer;
    }
}