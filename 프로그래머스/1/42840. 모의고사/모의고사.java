import java.util.*;

class Solution {
    
    static int[][] pattern = {
        {1,2,3,4,5},
        {2,1,2,3,2,4,2,5},
        {3,3,1,1,2,2,4,4,5,5}
    };
    
    static int n;
    static int[] scores;
    
    static int dfs(int depth, int[] pattern, int[] answers) {
        if (depth == n) {
            return 0;
        }
        
        int idx = depth % pattern.length;
        
        int score = pattern[idx] == answers[depth] ? 1 : 0;
        
        score += dfs(depth+1, pattern, answers);
        
        return score;
    }
    
    
    public int[] solution(int[] answers) {
        int[] answer = {};
        n = answers.length;
        
        int[] scores = new int[3];
        
        int max = 0;
        
        for (int i=0; i<3; i++) {
            scores[i] = dfs(0, pattern[i], answers);
            max = Math.max(max, scores[i]);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i=0; i<3; i++) {
            if (max == scores[i]) {
                list.add(i+1);
            }
        }
        
        Collections.sort(list);
        answer = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}