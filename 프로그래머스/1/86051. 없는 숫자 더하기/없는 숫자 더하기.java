import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int answer = -1;
        Set<Integer> set = new HashSet(Set.of(0,1,2,3,4,5,6,7,8,9));
        for (int n : numbers) {
            set.remove(n);
        }
        int n = 0;
        for (int s : set) {
            n += s;
        }
        answer = n;
        
        return answer;
    }
}