import java.util.*;

class Solution {
    static int n;
    static int answer;
    static ArrayList<Integer> result = new ArrayList<>();
    
    static void DFS(int L, int sum, int[] numbers, int target) {
        if (L == n) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        
        DFS(L+1, sum+numbers[L], numbers, target);
        DFS(L+1, sum-numbers[L], numbers, target);
    }
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        n = numbers.length;

        DFS(0, 0, numbers, target);
        return answer;
    }
}