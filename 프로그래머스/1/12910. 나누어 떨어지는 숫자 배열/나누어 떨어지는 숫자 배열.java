import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i:arr) {
            if (i%divisor==0) list.add(i);
        }
        
        answer = list.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(answer);
        
        if (answer.length < 1) {
            answer = Arrays.copyOf(answer, answer.length+1);
            answer[answer.length-1] = -1;
        }
        
        return answer;
    }
}