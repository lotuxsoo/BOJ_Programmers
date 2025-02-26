class Solution {
    
    static int[][] graph;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        graph = new int[n+1][n+1]; // 0 초기화
        
        for (int[] r : results) {
            graph[r[0]][r[1]] = 1;
            graph[r[1]][r[0]] = -1;
        }
        
        // 플로이드-워셜로 승패 추론
        for (int k=1; k<=n; k++) {
            for (int s=1; s<=n; s++) {
                for (int e=1; e<=n; e++) {
                    if (graph[s][k]==1 && graph[k][e]==1) {
                        graph[s][e] = 1;
                    } else if (graph[s][k]==-1 && graph[k][e]==-1) {
                        graph[s][e] = -1;
                    }
                }
            }
        }
        
        for (int i=1; i<=n; i++) {
            int cnt = 0;
            for (int j=1; j<=n; j++) {
                if (graph[i][j] != 0) cnt++;
            }
            if (cnt == n-1) answer++;
        }
        
        return answer;
    }
}