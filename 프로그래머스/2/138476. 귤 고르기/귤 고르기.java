import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer,Integer> map = new HashMap<>();
        for (int x : tangerine) {
            map.put(x, map.getOrDefault(x,0)+1);
        }
        
        List<Integer> values = new ArrayList<>(map.values());
        values.sort((a,b) -> Integer.compare(b,a));
        
        int idx = 0;
        while (k > 0) {
            k -= values.get(idx);
            idx++;
            answer++;
        }
        
        return answer;
    }
}