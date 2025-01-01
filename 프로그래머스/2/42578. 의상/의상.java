import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        
        // key:종류 value:개수
        Map<String, Integer> map = new HashMap<>();
        for (String[] arr : clothes) {
            map.put(arr[1], map.getOrDefault(arr[1],0)+1);
        }
        
        answer = 1;
        for (String key : map.keySet()) {
            answer *= map.get(key)+1;
        }
        answer -= 1; // 아무것도 선택안한 경우
        return answer;
    }
}