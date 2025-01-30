import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};
        answer = new int[commands.length];
        int idx = 0;
        
        for (int[] c : commands) {
            int[] temp = new int[c[1] - c[0] + 1];
            int j = 0;
            for (int i=c[0]-1; i<=c[1]-1; i++) {
                temp[j++] = array[i];
            }
            Arrays.sort(temp);
            answer[idx++] = temp[c[2] - 1];
        }
        
        return answer;
    }
}