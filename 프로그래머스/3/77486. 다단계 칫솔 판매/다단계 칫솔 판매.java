import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        
        // enroll, referral
        Map<String,String> referralMap = new HashMap<>();
        for (int i=0; i<enroll.length; i++) {
            referralMap.put(enroll[i], referral[i]);
        }

        // enroll, result
        Map<String,Integer> resultMap = new HashMap<>();
        for (int i=0; i<enroll.length; i++) {
            resultMap.put(enroll[i], 0);
        }
        
        for (int i=0; i<seller.length; i++) {
            String curSeller = seller[i];
            int curAmount = amount[i] * 100;
            
            while (curAmount > 0 && !curSeller.equals("-")) {
                int remain = curAmount - curAmount / 10;
                resultMap.put(curSeller, resultMap.get(curSeller)+remain);
                
                curAmount = curAmount / 10;
                curSeller = referralMap.get(curSeller);
            }
        }
        
        answer = new int[enroll.length];
        for (int i=0; i<enroll.length; i++) {
            answer[i] = resultMap.get(enroll[i]);
        }
        
        return answer;
    }
}