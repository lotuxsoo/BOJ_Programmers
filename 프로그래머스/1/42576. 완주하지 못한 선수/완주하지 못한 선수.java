import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String s : participant) {
            map.put(s, map.getOrDefault(s,0)+1);
        }
        
        for (String c : completion) {
            if (!map.containsKey(c)) break;
            
            map.put(c, map.get(c)-1);
            
            if (map.get(c) < 1) {
                map.remove(c);
            }
        }
        
        if (map.size() > 0) {
            for (String key : map.keySet()) {
                answer = key;
                break;
            }
        }
        
        return answer;
    }
}