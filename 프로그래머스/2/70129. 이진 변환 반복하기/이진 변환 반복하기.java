class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        
        int cntTransform = 0;
        int cntZeros = 0;
        
        while (!s.equals("1")) {
            cntTransform++;
            
            int zero = s.replace("1","").length(); // 0만 남음
            cntZeros += zero;
            
            s = Integer.toBinaryString(s.length() - zero);
        }
        
        answer = new int[]{cntTransform, cntZeros};
        
        return answer;
    }
}