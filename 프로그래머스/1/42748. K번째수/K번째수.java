import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};
        answer = new int[commands.length];
        
        int idx = 0;
        for (int[] command : commands) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i=command[0]-1; i<=command[1]-1; i++) {
                list.add(array[i]);
            }
            Collections.sort(list);
            answer[idx++] = list.get(command[2]-1);
        }
        
        return answer;
    }
}