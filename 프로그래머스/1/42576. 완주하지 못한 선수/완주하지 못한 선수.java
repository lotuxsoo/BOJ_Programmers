import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String,Integer> parMap = new HashMap<>();
        for (String s : participant) {
            parMap.put(s, parMap.getOrDefault(s,0)+1);
        }  
        
        for (String s : completion) {
            parMap.put(s, parMap.get(s)-1);
        }
        
        for (String key : parMap.keySet()) {
            if (parMap.get(key) > 0) {
                answer = key;
                break;
            }
        }

        return answer;
    }
}