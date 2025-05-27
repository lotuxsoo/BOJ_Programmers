import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] arr = new int[n+1];
        Arrays.fill(arr, 1);
        for (int i=1; i<=n; i++) {
            for (int x : lost) {
                if (i == x) {
                    arr[i]--;
                    break;
                }
            }
            for (int x : reserve) {
                if (i == x) {
                    arr[i]++;
                    break;
                }
            }
        }
        
        answer = n;
        
        for (int i=1; i<=n; i++) {
            if (arr[i] < 1) {
                boolean flag = false;
                if ((i-1 >= 1) && arr[i-1] > 1) {
                    arr[i-1]--;
                    arr[i]++;
                    flag = true;
                }
                if (!flag && (i+1 <= n) && arr[i+1] > 1) {
                    arr[i+1]--;
                    arr[i]++;
                    flag = true;
                }
                if (!flag) answer--;
            }
        }
        
        return answer;
    }
}