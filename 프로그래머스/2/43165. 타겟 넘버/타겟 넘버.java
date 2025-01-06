import java.util.*;

class Solution {
    static int cnt = 0;
    static int[] numbers;
    static int target;
    
    static void DFS(int start, int sum) {
        if (start == numbers.length) {
            if (sum == target) {
                cnt++;
            }
            return;
        }
        
        DFS(start+1, sum + numbers[start]);
        DFS(start+1, sum - numbers[start]);
    }
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        this.numbers = numbers;
        this.target = target;
        
        DFS(0, 0);
        
        answer = cnt;
        
        return answer;
    }
}