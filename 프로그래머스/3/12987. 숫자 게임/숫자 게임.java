import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        int n = A.length;
        
        int a = n-1, b = n-1;
        while (a >= 0 && b >= 0) {
            if (B[b] > A[a]) {
                answer++;
                b--;
                a--;
            } else {
                a--;
            }
        }
        
        return answer;
    }
}