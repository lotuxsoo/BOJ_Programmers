class Solution {
    public String solution(String s) {
        String answer = "";
        
        if (s.length()%2 == 0) {
            int index = s.length()/2;
            answer = s.substring(index-1,index+1);
        } else {
            int index = s.length()/2;
            answer = s.substring(index,index+1);
        }
        
        return answer;
    }
}