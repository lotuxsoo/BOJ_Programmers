import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        
        int[] A = new int[N+2]; // 1~N
        int total = stages.length;
        for (int x : stages) {
            A[x]++;
        }
        
        // stage 번호,실패율
        Map<Integer,Double> map = new HashMap<>();
        for (int i=1; i<=N; i++) {
            double failure = total == 0 ? 0 : ((double) A[i]) / total;
            total -= A[i];
            map.put(i, failure);
        }
        
        List<Map.Entry<Integer,Double>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1,o2) -> Double.compare(o2.getValue(), o1.getValue()));
        
        answer = new int[N];
        int i = 0;
        for (Map.Entry<Integer,Double> entry : list) {
            answer[i++] = entry.getKey();
        }
        
        return answer;
    }
}