import java.util.*;

class Solution {
    static class Ticket {
        String start;
        String end;
        Ticket(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }
    
    static ArrayList<String> answerList = new ArrayList<>();
    static ArrayList<Ticket> ticketList;
    static boolean[] visited;
    
    static void DFS(int cnt, String start, StringBuilder sb) {
        if (cnt == ticketList.size()) {
            answerList.add(sb.toString());
            return;
        }
        
        for (int i=0; i<ticketList.size(); i++) {
            if (!visited[i] && ticketList.get(i).start.equals(start)) {
                visited[i] = true;
                StringBuilder nsb = new StringBuilder(sb).append(ticketList.get(i).end).append(" ");
                DFS(cnt+1, ticketList.get(i).end, nsb);
                visited[i] = false;
            }
        }
    } 
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        visited = new boolean[tickets.length];
        
        ticketList = new ArrayList<>();
        
        for (String[] ticket : tickets) {
            ticketList.add(new Ticket(ticket[0], ticket[1]));
        }
        
        Collections.sort(ticketList, (o1,o2) -> o1.end.compareTo(o2.end));
        
        DFS(0, "ICN", new StringBuilder("ICN "));
        
        Collections.sort(answerList);
        
        answer = answerList.get(0).split(" ");
        
        return answer;
    }
}