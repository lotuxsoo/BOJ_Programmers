import java.util.*;

class Solution {
    
    static int n;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static int ans = 0;
    
    static void DFS(int x, int sum, int target) {
        if (x == n) {
            if (sum == target) ans++;
            return;
        }
        
        for (int next : adjList.get(x)) {
            DFS(x+1, sum+next, target);
        }
    }
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        for (int i=0; i<numbers.length; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i=0; i<numbers.length; i++) {
            adjList.get(i).add(numbers[i]);
            adjList.get(i).add(numbers[i]*-1);
        }
        
        n = numbers.length;
        
        DFS(0, 0, target);
        
        answer = ans;
        
        return answer;
    }
}