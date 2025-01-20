class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        
        int i = 0, j = 0;
        
        boolean flag = false;
        
        for (int x=0; x<goal.length; x++) {
            String cur = goal[x];
            
            if (i < cards1.length || j < cards2.length) {
                if (i < cards1.length && cards1[i].equals(cur)) {
                    i++;
                } else if (j < cards2.length && cards2[j].equals(cur)) {
                    j++;
                } else {
                    flag = true;
                    break;
                }
            }  
        }
        
        if (flag) return "No";
        
        return answer;
    }
}