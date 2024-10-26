class Solution {
    static boolean[] check;
    
    static void DFS(int i, int[][] computers, int n) {
        check[i] = true;
        for (int j=0; j<n; j++) {
            if (!check[j] && computers[i][j]==1) {
                DFS(j, computers, n);
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        check = new boolean[n];
        
        for (int i=0; i<n; i++) {
            if (!check[i]) { // 아직 방문되지 않은 컴퓨터가 있으면
                answer++; // 새로운 네트워크 발견
                DFS(i, computers, n); // 해당 네트워크에 속한 모든 컴퓨터 탐색
            }
        }

        return answer;
    }
}