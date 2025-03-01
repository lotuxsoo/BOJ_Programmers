import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] map = new int[rows][columns];
        for (int r=0; r<rows; r++) {
            for (int c=0; c<columns; c++) {
                map[r][c] = r*columns + c+1;
            }
        }
        
        for (int q=0; q<queries.length; q++) {
            int[] query = queries[q];
            int x1 = query[0]-1, y1 = query[1]-1, x2 = query[2]-1, y2 = query[3]-1;
                   
            List<Integer> ring = new ArrayList<>();
            
            // 상단 가로줄
            for (int c=y1; c<=y2; c++) {
                ring.add(map[x1][c]);
            }
            
            // 오른쪽 세로줄
            for (int r=x1+1; r<=x2; r++) {
                ring.add(map[r][y2]);
            }
            
            // 하단 가로줄
            for (int c=y2-1; c>=y1; c--) {
                ring.add(map[x2][c]);
            }
            
            // 왼쪽 세로줄
            for (int r=x2-1; r>x1; r--) {
                ring.add(map[r][y1]);
            }
            
            // 한칸 회전
            int last = ring.get(ring.size()-1);
            ring.remove(ring.size()-1);
            ring.add(0, last);
            
            int min = Integer.MAX_VALUE;
            for (int x : ring) {
                min = Math.min(min, x);
            }
            answer[q] = min;
            
            int idx = 0;
            
            // 상단 가로줄
            for (int c=y1; c<=y2; c++) {
                map[x1][c] = ring.get(idx++);
            }
            
            // 오른쪽 세로줄
            for (int r=x1+1; r<=x2; r++) {
                map[r][y2] = ring.get(idx++);
            }
            
            // 하단 가로줄
            for (int c=y2-1; c>=y1; c--) {
                map[x2][c] = ring.get(idx++);
            }
            
            // 왼쪽 세로줄
            for (int r=x2-1; r>x1; r--) {
                map[r][y1] = ring.get(idx++);
            }
        }
        
        return answer;
    }
}