import java.util.*;

class Solution {
    
    public int solution(int[] nums) {
        int answer = 0;
        
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int types = set.size();
        answer = Math.min (nums.length/2, types);
        
        return answer;
    }
}