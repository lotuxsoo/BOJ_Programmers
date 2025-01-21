import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        Map<String,Integer> target = new HashMap<>();
        for (int i=0; i<want.length; i++) {
            target.put(want[i], number[i]);
        }
        
        Map<String,Integer> map = new HashMap<>();
        for (int i=0; i<10; i++) {
            map.put(discount[i], map.getOrDefault(discount[i],0)+1);
        }
        
        if (target.equals(map)) answer++;
        
        int index = 10;
        while (index < discount.length) {
            map.put(discount[index], map.getOrDefault(discount[index],0)+1);
            map.put(discount[index-10], map.getOrDefault(discount[index-10],0)-1);
            
            if (map.getOrDefault(discount[index - 10], 0) < 1) {
                map.remove(discount[index - 10]);
            }
            
            if (target.equals(map)) answer++;
            index++;
        }

        return answer;
    }
}