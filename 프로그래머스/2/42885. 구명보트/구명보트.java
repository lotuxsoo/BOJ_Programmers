import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int l = 0, r = people.length-1;
        while (l < r) {
            int temp = people[l] + people[r];
            
            if (temp <= limit) {
                l++;
                r--;
            } else {
                r--;
            }
            
            answer++;
        }
        if (l == r) answer++;
        
        
        return answer;
    }
}