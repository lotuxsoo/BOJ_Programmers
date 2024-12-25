import java.util.*;

class Solution {
    
    static int n;
    static int ans = 0;
    static boolean[] visited;
    
    static void DFS(int x, int sum, int[] numbers, int target) {
        if (x == n) {
            if (sum == target) ans++;
            return;
        }
    
        DFS(x+1, sum+numbers[x], numbers, target);
        DFS(x+1, sum-numbers[x], numbers, target);            
    }
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        n = numbers.length;
        visited = new boolean[n];
        
        DFS(0, 0, numbers, target);
        
        answer = ans;
        
        return answer;
    }
}