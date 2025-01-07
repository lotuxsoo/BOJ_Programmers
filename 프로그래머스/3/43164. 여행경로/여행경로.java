import java.util.*;

class Solution {
    
    static List<String> ansList = new ArrayList<>();
    static boolean[] visited;
    
    static void DFS(int depth, String start, StringBuilder sb, String[][] tickets) {
        if (depth == tickets.length) {
            ansList.add(sb.toString());
            return;
        }
        
        for (int i=0; i<tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(start)) {
                visited[i] = true;
                StringBuilder nsb = new StringBuilder(sb).append(tickets[i][1]).append(" ");
                DFS(depth+1, tickets[i][1], nsb, tickets);
                visited[i] = false;
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        visited = new boolean[tickets.length]; // 티겟 개수만큼
        
        DFS(0, "ICN", new StringBuilder("ICN "), tickets);
        
        Collections.sort(ansList);
        
        answer = ansList.get(0).split(" ");
        
        return answer;
    }
}