import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        int[] scores = new int[3]; // 점수 배열
        
        int[] pat1 = {1,2,3,4,5};
        int[] pat2 = {2,1,2,3,2,4,2,5};
        int[] pat3 = {3,3,1,1,2,2,4,4,5,5};
        
        for (int i=0; i<answers.length; i++) {
            int ans = answers[i];
            
            if (ans == pat1[i % pat1.length]) scores[0] += 1;
            if (ans == pat2[i % pat2.length]) scores[1] += 1;
            if (ans == pat3[i % pat3.length]) scores[2] += 1;
        }
        
        int maxScore = Math.max(scores[0], Math.max(scores[1],scores[2]));
        List<Integer> result = new ArrayList<>();
        for (int i=0; i<3; i++) {
            if (scores[i] == maxScore) {
                result.add(i+1);
            }
        }        
        
        answer = result.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}