import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] map = new int[rows][columns];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                map[i][j] = i*columns + j+1;
            }
        }
        
        for (int q=0; q<queries.length; q++) {
            int[] query = queries[q];
            int x1 = query[0]-1, y1 = query[1]-1, x2 = query[2]-1, y2 = query[3]-1;
        
            // 시계방향 (오른쪽 -> 위쪽 -> 왼쪽 -> 아래쪽) 
            int n = x2 - x1;
            int m = y2 - y1;
            
            int firstNum = map[x1][y1+m]; // 오른쪽 방향 가장 끝
            int min = firstNum;
            
            for (int i=m; i>0; i--) { // 오른쪽
                map[x1][y1+i] = map[x1][y1+i-1];
                min = Math.min(min, map[x1][y1+i]);
            }
            
            for (int i=1; i<=n; i++) { // 위쪽
                map[x1+i-1][y1] = map[x1+i][y1];
                min = Math.min(min, map[x1+i-1][y1]);
            }
            
            for (int i=m; i>0; i--) { // 왼쪽
                map[x2][y2-i] = map[x2][y2-i+1];  
                min = Math.min(min, map[x2][y2-i]);
            }
            
            for (int i=1; i<=n; i++) { // 아래쪽
                map[x2-i+1][y2] = map[x2-i][y2];
                min = Math.min(min, map[x2-i+1][y2]);
            }
            
            map[x1+1][y1+m] = firstNum;

            answer[q] = min;
        }
        
        return answer;
    }
}