class Solution {
    public String solution(String number, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<number.length(); i++) {
            char now = number.charAt(i);
            while (k>0 && sb.length()>0 && sb.charAt(sb.length()-1) < now) {
                sb.deleteCharAt(sb.length()-1);
                k--;
            }
            sb.append(now);
        }
        
        while (k-- > 0) {
            sb.deleteCharAt(sb.length()-1);
        }

        answer = sb.toString();
        
        return answer;
    }
}