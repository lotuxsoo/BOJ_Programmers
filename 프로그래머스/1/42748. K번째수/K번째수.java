import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i=0; i<commands.length; i++) {
            int[] command = commands[i];
            int a = command[0]-1;
            int b = command[1];
            int c = command[2]-1;
            
            int[] newArr = Arrays.copyOfRange(array, a, b);
            Arrays.sort(newArr);
            result.add(newArr[c]);
        }
        
        answer = new int[result.size()];
        for (int i=0; i<answer.length; i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}