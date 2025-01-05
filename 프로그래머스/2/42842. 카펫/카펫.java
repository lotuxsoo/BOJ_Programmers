import java.util.*;

class Solution {
    
    static int[] getPair(int total, int brown, int yellow) {
        List<int[]> pairs = new ArrayList<>();    
        
        for (int i=1; i<=Math.sqrt(total); i++) {
            if (total % i == 0) {
                pairs.add(new int[]{i, total/i});
            }
        }
        
        for (int[] pair : pairs) {
            int row = Math.min(pair[0], pair[1]); // 세로
            int col = Math.max(pair[0], pair[1]); // 가로
            
            if ((col-2)*(row-2) == yellow && col*2+(row-2)*2 == brown) {
                return new int[]{col, row};
            }
        }
        
        return new int[]{0,0};
    }
    
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        
        int total = brown + yellow;
        int[] pair = getPair(total, brown, yellow);
        
        answer = pair;
        
        return answer;
    }
}