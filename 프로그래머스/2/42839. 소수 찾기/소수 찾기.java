import java.util.*;

class Solution {
    
    static ArrayList<String> list = new ArrayList<>();
    static boolean[] visited;
    static Set<Integer> ans = new HashSet<>();
    
    static void DFS(String str) {
        if (str != "" && isPrime(str)) {
            ans.add(Integer.parseInt(str));
        }
        
        for (int i=0; i<list.size(); i++) {
            String s = list.get(i);
            if (!visited[i]) {
                visited[i] = true;
                DFS(str + s);
                visited[i] =  false;
            }
        }   
    }
    
    static boolean isPrime(String s) {
        int num = Integer.parseInt(s);
        if (num <= 1) return false;
        for (int i=2; i<num; i++) {
            if (num%i == 0) return false;
        }
        return true;
    }
    
    public int solution(String numbers) {
        int answer = 0;
        
        for (String s : numbers.split("")) {
            list.add(s);
        }
        visited = new boolean[list.size()];
        
        DFS("");
        
        answer = ans.size();
        
        return answer;
    }
}