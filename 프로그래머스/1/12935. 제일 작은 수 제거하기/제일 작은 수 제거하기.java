import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        
        ArrayList<Integer> list = new ArrayList<>();
        for (int i:arr) {
            list.add(i);
        }
        
        int min = Arrays.stream(arr).min().getAsInt();
        
        list.remove(Integer.valueOf(min));
        
        if (list.size() < 1) list.add(-1);
        
        answer = list.stream().mapToInt(n -> n).toArray();
        
        return answer;
    }
}