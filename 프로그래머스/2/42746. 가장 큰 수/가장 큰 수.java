import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        String[] arr = new String[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(arr, (o1,o2) -> (o2+o1).compareTo(o1+o2));
        
        for (String s : arr) {
            answer += s;
        }
        
        if (answer.startsWith("0")) {
            return "0";
        }
        
        return answer;
    }
}