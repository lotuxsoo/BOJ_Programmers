import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        
        Map<String,String> userMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        
        for (String s : record) {
            if (s.startsWith("Enter")) {
                String[] tokens = s.split(" ");
                userMap.put(tokens[1], tokens[2]);
                sb.append(tokens[1]).append(" Enter ");
            } else if (s.startsWith("Leave")) {
                String[] tokens = s.split(" ");
                sb.append(tokens[1]).append(" Leave ");
            } else if (s.startsWith("Change")) {
                String[] tokens = s.split(" ");
                userMap.put(tokens[1], tokens[2]);
            }
        }
        
        String[] splits = sb.toString().split(" ");
        
        ArrayList<String> list = new ArrayList<>();
        
        StringBuilder nsb = new StringBuilder();
        for (String token : splits) {
            if (userMap.containsKey(token)) {
                nsb.append(userMap.get(token));
                continue;
            } else {
                if (token.equals("Enter")) {
                    nsb.append("님이 들어왔습니다.");
                } else if (token.equals("Leave")) {
                    nsb.append("님이 나갔습니다.");
                }
            }
            
            list.add(nsb.toString());
            nsb = new StringBuilder();
        }
        
        answer = new String[list.size()];
        for (int i=0; i<answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}