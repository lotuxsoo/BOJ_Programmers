import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        // 오름차순 정렬
        Arrays.sort(citations);
        
        int n = citations.length;
        
        for (int i=0; i<n; i++) {
            int h = n-i; // i보다 큰 논문의 개수
            if (citations[i] >= h) {
                return h;
            }
        }
        
        return answer;
    }
}