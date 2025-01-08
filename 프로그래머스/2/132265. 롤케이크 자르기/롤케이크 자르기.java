import java.util.*;

class Solution {

    public int solution(int[] topping) {
        int answer = 0;
        
        Map<Integer, Integer> rightMap = new HashMap<>();
        Map<Integer, Integer> leftMap = new HashMap<>();
        
        for (int i=0; i<topping.length; i++) { // n번
            rightMap.put(topping[i], rightMap.getOrDefault(topping[i],0)+1);
        }
        
        for (int i=0; i<topping.length; i++) {
            int now = topping[i];
            if (rightMap.containsKey(now)) {
                rightMap.put(now, rightMap.get(now)-1);
                if (rightMap.get(now) <= 0) {
                    rightMap.remove(now);
                }
            }
            
            leftMap.put(now, leftMap.getOrDefault(now,0)+1);
            if (leftMap.size() == rightMap.size()) answer ++;
        }
        
        return answer;
    }
}