class Solution {
    public String solution(int n) {
        String answer = "";
        
        int x = 0;
        while (n-- > 0) {
            if (x%2 == 0) {
                answer += "수";
            }
            else {
                answer += "박";
            }
            x++;
        }
        
        return answer;
    }
}