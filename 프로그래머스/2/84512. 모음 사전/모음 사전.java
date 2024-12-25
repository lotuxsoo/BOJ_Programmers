import java.util.*;

class Solution {
    
    static int MAX = 5;
    static String[] moeum = {"A","E","I","O","U"};
    static List<String> list = new ArrayList<>();
    
    static void DFS(String current, String word) {
        list.add(current);
        
        if (current.length() == MAX || current.equals(word)) {
            return;
        }

        for (int i=0; i<moeum.length; i++) {
            DFS(current+moeum[i], word);
        }
    }
    
    public int solution(String word) {
        int answer = 0;
        
        DFS("", word);
        
        for (String s : list) {
            if (s.equals(word)) break;
            answer++;
        }
        
        return answer;
    }
}