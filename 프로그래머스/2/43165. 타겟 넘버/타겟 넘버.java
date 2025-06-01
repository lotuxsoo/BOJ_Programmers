import java.util.*;

class Solution {
    
    static int n;
    static int result = 0;
    
    static void dfs(int index, int sum, int[] numbers, int target) {
        if (index == n) {
            if (sum == target) {
                result++;
            }
            return;
        }
        
        dfs(index+1, sum+numbers[index], numbers, target);
        dfs(index+1, sum-numbers[index], numbers, target);
    }
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        n = numbers.length;
        
        dfs(0, 0, numbers, target);
        
        answer = result;
        
        return answer;
    }
}