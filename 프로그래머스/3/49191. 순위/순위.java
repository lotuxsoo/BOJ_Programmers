import java.util.*;

class Solution {
    
    static ArrayList<Integer>[] wins; // 내가 이긴애 더하기
    static ArrayList<Integer>[] loses; // 내가 진애 추가
    static int[] count;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        wins = new ArrayList[n+1];
        loses = new ArrayList[n+1];
        for (int i=0; i<n+1; i++) {
            wins[i] = new ArrayList<>();
            loses[i] = new ArrayList<>();
        }
        
        for (int[] row : results) {
            wins[row[0]].add(row[1]);
            loses[row[1]].add(row[0]);
        }
        
        count = new int[n+1];
        
        // 이긴애 전파
        for (int i=1; i<=n; i++) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            boolean[] visited = new boolean[n+1];
            visited[i] = true;
            int cnt = 0;
            
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                
                for (int next : wins[cur]) { // 내가 이긴애들 탐색
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                        cnt++;
                    }
                }
            } 
            
            count[i] += cnt;
            
            // 진애 전파
            queue = new LinkedList<>();
            queue.add(i);
            visited = new boolean[n+1];
            visited[i] = true;
            cnt = 0;
            
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                
                for (int next : loses[cur]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                        cnt++;
                    }
                }
            }  
            
            count[i] += cnt;
        }

        for (int i=1; i<=n; i++) {
            if (count[i] == n-1) answer++;
        }  
        
        return answer;
    }
}