class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        int[][] students = {
            {1,2,3,4,5},
            {2,1,2,3,2,4,2,5},
            {3,3,1,1,2,2,4,4,5,5}
        };
        
        int[] result = new int[3];
        
        for (int i=0; i<answers.length; i++) {
            
            for (int j=0; j<students.length; j++) { // 3번
                int[] student = students[j];
            
                if (student[i % student.length] == answers[i]) {
                    result[j]++;
                }   
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int x : result) {
            if (x > max) {
                max = x;
            }
        }
        
        int count = 0;
        for (int x : result) {
            if (x == max) {
                count++;
            }
        }
        
        answer = new int[count];
        int index = 0;
        for (int i=0; i<result.length; i++) {
            if (result[i] == max) {
                answer[index++] = i+1;
            }
        }
        
        return answer;
    }
}