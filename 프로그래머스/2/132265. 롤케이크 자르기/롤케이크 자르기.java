import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        // topping 원소,개수
        Map<Integer,Integer> map = new HashMap<>();
        for (int t : topping) {
            map.put(t, map.getOrDefault(t,0)+1);
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<topping.length; i++) {
            if (map.containsKey(topping[i])) {
                if (map.getOrDefault(topping[i],0)-1 <= 0) {
                    map.remove(topping[i]);
                } else {
                    map.put(topping[i], map.get(topping[i])-1);
                }
                set.add(topping[i]);
                if (map.size() == set.size()) answer++;
            }
        }
        
        return answer;
    }
}