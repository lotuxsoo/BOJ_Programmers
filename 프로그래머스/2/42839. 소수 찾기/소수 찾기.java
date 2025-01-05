import java.util.*;

class Solution {
    
    static Set<Integer> result = new HashSet<>();
    static boolean[] visited;
    
    static boolean isPrime(String str) {
        int num = Integer.parseInt(str);
        
        if (num <= 1) return false;
        
        for (int i=2; i<num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
    
    static void permute(String[] splits, String s, int r) {
        if (r == splits.length) {
            return;
        }
        
        for (int i=0; i<splits.length; i++) {            
            if (!visited[i]) {
                visited[i] = true;
                s += splits[i];

                if (isPrime(s)) {
                    result.add(Integer.parseInt(s));
                }
                
                permute(splits, s, r+1);
                visited[i] = false;
                s = s.substring(0, s.length()-1);
            }
        }
    }
    
    public int solution(String numbers) {
        int answer = 0;
        
        String[] splits = numbers.split("");
        
        visited = new boolean[numbers.length()];
            
        //순열 만들기
        permute(splits, "", 0);
        
        answer = result.size();
        
        return answer;
    }
}