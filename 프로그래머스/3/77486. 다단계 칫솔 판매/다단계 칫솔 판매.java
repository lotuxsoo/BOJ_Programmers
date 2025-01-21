import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        
        Map<String,String> parentMap = new HashMap<>();
        for (int i=0; i<enroll.length; i++) {
            parentMap.put(enroll[i], referral[i]);
        }
        
        Map<String,Integer> benefitMap = new HashMap<>();
        
        for (int i=0; i<seller.length; i++) {
            String curName = seller[i];
            int money = amount[i]*100;
            
            while (!curName.equals("-") && money > 0) {
                benefitMap.put(curName, benefitMap.getOrDefault(curName,0) + money - (money/10));
                curName = parentMap.get(curName);
                money /= 10;  
            }
        }
        
        answer = new int[enroll.length];
        for (int i=0; i<answer.length; i++) {
            answer[i] = benefitMap.getOrDefault(enroll[i],0);
        }
        
        return answer;
    }
}