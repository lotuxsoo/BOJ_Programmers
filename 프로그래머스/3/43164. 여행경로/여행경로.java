import java.util.*;

class Solution {
    
    static ArrayList<String> ansList = new ArrayList<>();
    static Map<String, PriorityQueue<String>> map = new HashMap<>();
    
    static void DFS(String x) {
        PriorityQueue<String> pq = map.get(x);
        
        while (pq != null && !pq.isEmpty()) {
            String next = pq.poll();
            DFS(next);                    
        }
        
        ansList.add(x);
    }
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            map.putIfAbsent(from, new PriorityQueue<>());
            map.get(from).add(to);
        }
        
        DFS("ICN");
        
        Collections.reverse(ansList);
        answer = new String[ansList.size()];
        for (int i=0; i<answer.length; i++) {
            answer[i] = ansList.get(i);
        }
        
        return answer;
    }
}