import java.util.*;

class Solution {
    
    // 인자로 주어진 리스트(또는 전역 리스트)를 수정하는 패턴
    static void dfs(List<String> results, String from) {
        
        // from -> to가 존재하는 경우 반복해서 재귀
        while (map.containsKey(from) && !map.get(from).isEmpty()) {
            dfs(results, map.get(from).poll());
        }
        
        results.add(0, from);
    }
    
    static Map<String, PriorityQueue<String>> map = new HashMap<>();
    static int N; // 티켓 개수
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        N = tickets.length;
        
        for (String[] ticket : tickets) {
            map.putIfAbsent(ticket[0], new PriorityQueue<>((a,b)->a.compareTo(b)));
            map.get(ticket[0]).add(ticket[1]);
        }
        
        // 인자로 주어진 리스트(또는 전역 리스트)를 수정하는 패턴
        List<String> results = new LinkedList<>(); 
        
        dfs(results, "ICN");
        
        answer = results.toArray(new String[0]);
        
        return answer;
    }
}