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
    
    static ArrayList<Integer>[] A;
    static int MAX_VAL = Integer.MIN_VALUE;
    
    static void BFS(int[] info) {
    Queue<Status> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();

    // 초기 상태: 루트 노드(0)부터 탐색 시작
    queue.add(new Status(1, 0, new HashSet<>(Set.of(0))));

    while (!queue.isEmpty()) {
        Status cur = queue.poll();
        int zero = cur.zero;
        int one = cur.one;
        Set<Integer> nodes = cur.nodes;

        // 상태 갱신: 현재 0의 최대값
        MAX_VAL = Math.max(MAX_VAL, zero);

        // 상태를 문자열로 변환하여 방문 여부 확인
        String state = zero + "," + one + "," + nodes.toString();
        if (visited.contains(state)) continue;
        visited.add(state);

        // 현재 노드 집합에서 자식 노드 탐색
        for (int node : nodes) {
            for (int child : A[node]) {
                if (nodes.contains(child)) continue; // 이미 방문한 노드 제외

                Set<Integer> newNodes = new HashSet<>(nodes);
                newNodes.add(child); // 자식 노드 추가

                if (info[child] == 1 && zero > one + 1) {
                    // 늑대를 추가로 선택할 수 있는 경우
                    queue.add(new Status(zero, one + 1, newNodes));
                } else if (info[child] == 0) {
                    // 양을 추가로 선택할 수 있는 경우
                    queue.add(new Status(zero + 1, one, newNodes));
                }
            }
        }
    }
}
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        
        A = new ArrayList[info.length];
        for (int i=0; i<info.length; i++) {
            A[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            A[edge[0]].add(edge[1]);
        }
        
        BFS(info);
        
        answer = MAX_VAL;
        
        return answer;
    }
}