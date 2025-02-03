import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int n = tangerine.length;
        
        Map<Integer,Integer> map = new HashMap<>();

        for (int x : tangerine) {
            map.put(x, map.getOrDefault(x,0)+1);
        }
        
        List<Map.Entry<Integer,Integer>> keys = new ArrayList<>(map.entrySet());
        
        // value 내림차순 정렬
        keys.sort((a,b) -> Integer.compare(b.getValue(),a.getValue()));
        
        int idx = 0;
                  
        while (k > 0) {
            k -= keys.get(idx).getValue();
            answer++;
            idx++;
        }
        
        return answer;
    }
}