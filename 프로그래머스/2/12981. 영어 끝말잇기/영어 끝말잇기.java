import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {};
        
        int[] person = new int[n+1];

        Set<String> wordSet = new HashSet<>();
        
        // 첫번째 단어 처리
        wordSet.add(words[0]);
        char last = words[0].charAt(words[0].length()-1);
        person[0] = 1;
        
        int idx = 1;
        
        for (int i=1; i<words.length; i++) {
            idx = idx % n;
            
            if (wordSet.contains(words[i]) || words[i].length() == 1) {
                return new int[]{idx+1, person[idx]+1};
            }
            
            char first = words[i].charAt(0);
            if (last != first) {
               return new int[]{idx+1, person[idx]+1};
            }
            
            wordSet.add(words[i]);
            
            last = words[i].charAt(words[i].length()-1);
            
            person[idx]++;
            idx++;
        }

        return new int[]{0,0};
    }
}