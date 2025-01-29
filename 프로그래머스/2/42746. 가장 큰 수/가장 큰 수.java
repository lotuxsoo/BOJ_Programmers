import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        String[] strNums = Arrays.stream(numbers)
                                 .mapToObj(String::valueOf)
                                 .toArray(String[]::new);
        
        Arrays.sort(strNums, (a,b) -> (b+a).compareTo(a+b));
        
        if (strNums[0].equals("0")) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String s : strNums) {
            sb.append(s);
        }
        
        answer = sb.toString();
        
        return answer;
    }
}