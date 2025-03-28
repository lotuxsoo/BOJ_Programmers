import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] student = new int[n+1];
        Arrays.fill(student, 1);
        
        for (int x : lost) {
            student[x]--;
        }
        
        for (int x : reserve) {
            student[x]++;
        }
        
        // 그리디?
        for (int i=1; i<=n; i++) {
            if (student[i] == 0) {
                if (i-1 != 0 && student[i-1] == 2) {
                    student[i-1]--;
                    student[i]++;
                } else if (i+1 <= n && student[i+1] == 2) {
                    student[i+1]--;
                    student[i]++;
                }
            }
        }
        
        for (int i=1; i<=n; i++) {
            if (student[i] > 0) answer++;
        }
        
        return answer;
    }
}