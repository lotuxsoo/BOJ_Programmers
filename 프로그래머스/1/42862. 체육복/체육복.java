import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        HashSet<Integer> lostSet = new HashSet<>();
        for (int x : lost) {
            lostSet.add(x);
        }
        
        HashSet<Integer> reserveSet = new HashSet<>();
        for (int x : reserve) {
            if (!lostSet.contains(x)) {
                reserveSet.add(x);  
            } else {
                lostSet.remove(x);
            }
        }
        
        answer = n;
        
        for (int i=1; i<=n; i++) {
            if (lostSet.contains(i)) {
                boolean flag = false;
                
                if (i-1 >= 1) {
                    if (reserveSet.contains(i-1)) {
                        reserveSet.remove(i-1);
                        flag = true;
                    }
                }
                if ((i+1 <= n) && !flag) {
                    if (reserveSet.contains(i+1)) {
                        reserveSet.remove(i+1);
                        flag = true;
                    }
                }
                
                if (!flag) answer--;
            }
        }
        
        
        return answer;
    }
}