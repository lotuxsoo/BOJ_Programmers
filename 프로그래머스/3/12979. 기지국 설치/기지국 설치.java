class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
         
        int location = 1; // 1번부터 그리디 탐색
        
        int i = 0; // 기지국의 인덱스
        
        while (location <= n) {
            
            if (i < stations.length && location >= stations[i]-w) {      
                location = stations[i]+w+1;
                i++;    
            } else {
                location += 2*w+1;
                answer++;
            }
        }
        

        return answer;
    }
}