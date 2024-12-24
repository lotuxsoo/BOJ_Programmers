class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        
        int total = brown + yellow;
        
        // 조합 구하기 
        for (int i = 1; i <= Math.sqrt(total); i++) {
            if (total % i == 0) {
                int height = i;
                int width = total / i;
                if ((height-2) * (width-2) == yellow) {
                    answer = new int[]{width,height};
                }
            }
        }
        
        return answer;
    }
}