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
    
    static boolean[] visited;
    static ArrayList<String> ansList = new ArrayList<>();
    
    static void DFS(int depth, String start, StringBuilder sb, ArrayList<Ticket> ticketList) {
        if (depth == ticketList.size()) {
            ansList.add(sb.toString());
            return;
        }
        
        for (int i=0; i<ticketList.size(); i++) {
            if (!visited[i] && ticketList.get(i).start.equals(start)) {
                visited[i] = true;
                StringBuilder nsb = new StringBuilder(sb);
                nsb.append(ticketList.get(i).end).append(" ");
                DFS(depth+1, ticketList.get(i).end, nsb, ticketList);
                visited[i] = false;
            }
        }
    }

    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        ArrayList<Ticket> ticketList = new ArrayList<>();
        for (String[] ticket : tickets) {
            ticketList.add(new Ticket(ticket[0],ticket[1]));
        }
        
        // 티켓 도착지 이름순 정렬 
        Collections.sort(ticketList, (o1,o2) -> o1.end.compareTo(o2.end));
        
        visited = new boolean[ticketList.size()];
        
        DFS(0, "ICN", new StringBuilder("ICN").append(" "), ticketList);
        
        // 정답 문자열 배열 이름순 정렬
        Collections.sort(ansList);
        
        answer = ansList.get(0).split(" ");
        
        return answer;
    }
}