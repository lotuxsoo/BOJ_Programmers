import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        
        Arrays.sort(d);
        // for (int i=0; i<d.length/2; i++) {
        //     int temp = d[i];
        //     d[i] = d[d.length-1-i];
        //     d[d.length-1-i] = temp;
        // }
        
        for (int x : d) {
            if (x <= budget) {
                answer++;
                budget -= x;
                System.out.println(x);
            }
            if (budget <= 0) break;
        }
        
        return answer;
    }
}