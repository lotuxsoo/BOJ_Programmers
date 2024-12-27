import java.util.*;

class Solution {

    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length-1;
        
        boolean[] visited = new boolean[people.length];
        
        while (left < right) {      
            if (!visited[left] && !visited[right]) {
                int sum = people[left] + people[right];
                if (sum <= limit) {
                    visited[left] = true;
                    visited[right] = true;
                    answer++; // 보트 하나 완성
                } else {
                    right--;
                    continue;
                }
            }

            left++;
            right--;
        }
        
        for (boolean b : visited) {
            if (!b) answer++;
        }
        
        return answer;
    }
}