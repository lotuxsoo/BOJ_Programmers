import java.util.*;

class Solution {

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        
        Map<String,Integer> idMap = new HashMap<>();
        for (int i=0; i<id_list.length; i++) {
            idMap.put(id_list[i], i);
        }
        
        Map<String,Set<String>> reportMap = new HashMap<>();
        Map<String,Integer> warnMap = new HashMap<>();
        
        for (String str : report) {
            String[] splits = str.split(" ");
            reportMap.putIfAbsent(splits[1], new HashSet<>());
            
            if (!reportMap.get(splits[1]).contains(splits[0])) {
                reportMap.get(splits[1]).add(splits[0]);    
                warnMap.put(splits[1], warnMap.getOrDefault(splits[1],0)+1); 
            }
        }
        
        answer = new int[id_list.length];
        
        ArrayList<String> warnKeys = new ArrayList<>(warnMap.keySet());
        Collections.sort(warnKeys, (o1,o2) -> warnMap.get(o2) - warnMap.get(o1));
        for (String key : warnKeys) {
            if (warnMap.get(key) >= k) {
                Set<String> set = reportMap.get(key);
                for (String name : set) {
                    answer[idMap.get(name)]++;
                }                
            } else {
                break;
            }
        }
        
        return answer;
    }
}