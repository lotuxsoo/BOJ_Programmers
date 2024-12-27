import java.util.*;

class Solution {
    
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i=1; i<=n; i++) {
            map.put(i, map.getOrDefault(i,1));
        }
        
        for (int x : lost) {
            map.put(x, map.get(x)-1);
        }
        for (int x : reserve) {
            map.put(x, map.get(x)+1);
        }
        
        for (int i=1; i<=n; i++) {
            if (map.get(i) == 0) {
                if (i-1>=1 && map.get(i-1)==2) { // 앞 확인
                    map.put(i-1, 1);
                    map.put(i, 1);
                } else if (i+1<=n && map.get(i+1)==2) { // 뒤 확인
                    map.put(i+1, 1);
                    map.put(i, 1);
                }
            }   
        }
        
        for (int key : map.keySet()) {
            if (map.get(key) > 0) answer++;
        }
        
        return answer;
    }
}