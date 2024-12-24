import java.util.*;

class Solution {
    
    static Set<Integer> set = new HashSet<>();
    static boolean[] visited;
    static String string;
    
    static boolean isPrime(int x) {
        if (x <= 1) return false;
        for (int i=2; i<x; i++) {
            if (x%i == 0) return false;
        }
        return true;
    }
    
    static void permute(String current) {
        if (current != "") {
            set.add(Integer.parseInt(current));
        }
        
        for (int i=0; i<string.length(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            permute(current + string.charAt(i));
            visited[i] = false;
        }
    }
    
    public int solution(String numbers) {
        int answer = 0;
        string = numbers;
        visited = new boolean[string.length()];
        
        permute("");
        
        for (int i : set) {
            if (isPrime(i)) answer++;
        }
        
        return answer;
    }
}