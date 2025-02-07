import java.util.*;

class Solution {
    
    static ArrayList<String> ansList = new ArrayList<>();
    static boolean[] visited;
    
    static void DFS(int cnt, String start, StringBuilder sb, String[][] tickets) {
        if (cnt == tickets.length) {
            ansList.add(sb.toString());
            return;
        }
        
        for (int i=0; i<tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(start)) {
                visited[i] = true;
                StringBuilder nsb = new StringBuilder(sb).append(tickets[i][1]).append(" ");
                DFS(cnt+1, tickets[i][1], nsb, tickets);
                visited[i] = false;
            }
        }
        
    }
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        visited = new boolean[tickets.length];
        
        DFS(0, "ICN", new StringBuilder("ICN "), tickets);
        
        ansList.sort(Comparator.comparing(s -> s));
        
        if (ansList.size() > 0) {
            answer = ansList.get(0).split(" ");
        }
        
        return answer;
    }
}