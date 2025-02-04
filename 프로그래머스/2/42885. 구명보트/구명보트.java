import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people); // 몸무게 오름차순
        int n = people.length;
        
        int left = 0, right = n-1;
        
        while (left <= right && right >= 0) {
            int sum = people[left] + people[right];
            if (sum <= limit) {
                answer++;
                right--;
                left++;
            } else if (sum > limit) {
                answer++;
                right--;
            }
        }

        
        return answer;
    }
}