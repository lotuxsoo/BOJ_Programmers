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
    static ArrayList<ArrayList<String>> ansList = new ArrayList<>();
    
    static void DFS(int depth, String start, ArrayList<String> list, ArrayList<Ticket> ticketList) {
        if (depth == ticketList.size()) {
            ansList.add(new ArrayList<>(list));
            return;
        }
        
        for (int i=0; i<ticketList.size(); i++) {
            if (!visited[i] && ticketList.get(i).start.equals(start)) {
                visited[i] = true;
                list.add(ticketList.get(i).end); // 목적지 추가
                DFS(depth+1, ticketList.get(i).end, list, ticketList);
                list.remove(list.size()-1); // 백트래킹
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
        
        Collections.sort(ticketList, (o1, o2) -> {
            if (o1.start.equals(o2.start)) {
                return o1.end.compareTo(o2.end);
            }
            return o1.start.compareTo(o2.start);
        });
        
        visited = new boolean[ticketList.size()];
        
        ArrayList<String> list = new ArrayList<>();
        
        list.add("ICN");
        
        DFS(0, "ICN", list, ticketList);
        
        Collections.sort(ansList, (a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                int cmp = a.get(i).compareTo(b.get(i));
                if (cmp != 0) return cmp; // 사전순으로 앞서면 음수, 뒤면 양수 반환
            }
            return 0; // 모든 요소가 같으면 0 반환
        });
        
        answer = ansList.get(0).toArray(new String[0]);
        
        return answer;
    }
}