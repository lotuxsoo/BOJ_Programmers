import java.util.*;

class Solution {
    static class Status {
        int zero, one;
        Set<Integer> nodes;
        Status(int zero, int one, Set<Integer> nodes) {
            this.zero = zero;
            this.one = one;
            this.nodes = nodes;
        }
    }
    
    static int N;
    static ArrayList<Integer>[] graph;
    static int MAX_VAL = Integer.MIN_VALUE;
    
    static void BFS(int[] info) {
        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(1,0,new HashSet<>(Set.of(0))));
        Set<String> visited = new HashSet<>();
            
        while (!queue.isEmpty()) {
            Status cur = queue.poll();
            int zero = cur.zero, one = cur.one;
            MAX_VAL = Math.max(MAX_VAL, zero);
            
            Set<Integer> nodes = cur.nodes;
            // String s = zero+" "+one+" "+nodes.toString();
            // if (visited.contains(s)) continue;
            // else visited.add(s);
            
            for (int node : nodes) {
                for (int next : graph[node]) { // 다음 노드와 연결된 노드들
                    if (nodes.contains(next)) continue;
                    if ((info[next] == 1) && zero > one+1) {
                        Set<Integer> newNodes = new HashSet<>(nodes);
                        newNodes.add(next);
                        queue.add(new Status(zero,one+1,newNodes));
                    } else if (info[next] == 0) {
                        Set<Integer> newNodes = new HashSet<>(nodes);
                        newNodes.add(next);
                        queue.add(new Status(zero+1,one,newNodes));
                    }
                }
            }
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        N = info.length; // 노드의 수
        graph = new ArrayList[N]; // 에지 저장
        for (int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        
        BFS(info);
        
        answer = MAX_VAL;
        
        return answer;
    }
}