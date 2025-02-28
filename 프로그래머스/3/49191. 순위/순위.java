import java.util.*;

class Solution {
    
    static int[][] D;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        D = new int[n+1][n+1];
        
        for (int[] row : results) {
            D[row[0]][row[1]] = 1;
            D[row[1]][row[0]] = -1;
        }
        
        for (int k=1; k<=n; k++) {
            for (int s=1; s<=n; s++) {
                for (int e=1; e<=n; e++) {
                    if (D[s][k]==1 && D[k][e]==1) {
                        D[s][e] = 1;
                        D[e][s] = -1;
                    } else if (D[s][k]==-1 && D[k][e]==-1) {
                        D[s][e] = -1;
                        D[e][s] = 1;
                    }
                }
            }
        }
        
        for (int i=1; i<=n; i++) {
            int cnt = 0;
            for (int j=1; j<=n; j++) {
                if (D[i][j] != 0) cnt++;
            }
            if (cnt == n-1) answer++;
        }
        
        return answer;
    }
}