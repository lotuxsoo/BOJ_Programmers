import java.util.*;

class Solution {
    
    public int solution(int[] nums) {
        int answer = 0;
        int n = nums.length;
        
        // 해시맵에 담기
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i,0)+1);
        }
        
        // 고유한 포켓몬수 < N/2
        if (map.size() <= n/2) answer = map.size();
        
        // 고유한 포켓몬수 > N/2
        if (map.size() > n/2) answer = n/2;
        
        return answer;
    }
}