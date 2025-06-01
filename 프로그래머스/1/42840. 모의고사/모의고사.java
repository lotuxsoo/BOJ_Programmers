import java.util.*;

class Solution {
    
    static int[][] pattern = {
        {1,2,3,4,5},
        {2,1,2,3,2,4,2,5},
        {3,3,1,1,2,2,4,4,5,5}
    };
    
    static int solve(int depth, int[] p, int[] answers) {
        if (depth == answers.length) {
            return 0;
        }
        
        int idx = depth % p.length;
        int score = p[idx] == answers[depth] ? 1 : 0;
        score += solve(depth+1, p, answers);
        return score;
    }
    
    public int[] solution(int[] answers) {
        int[] answer = {};
        int n = answers.length;
        
        int[] scores = new int[3];
        int max = 0;
        
        for (int i=0; i<3; i++) {
            scores[i] = solve(0, pattern[i], answers);
            max = Math.max(max, scores[i]);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i=0; i<3; i++) {
            if (max == scores[i]) {
                list.add(i+1);
            }
        }
        
        answer = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}