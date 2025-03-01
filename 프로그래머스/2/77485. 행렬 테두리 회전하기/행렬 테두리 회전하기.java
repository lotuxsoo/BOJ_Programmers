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
            int x1 = query[0]-1, y1 = query[1]-1;
            int x2 = query[2]-1, y2 = query[3]-1;
            
            int prev = map[x1][y2]; // 최상단행 가장 오른쪽 숫자
            int MIN = prev;
            
            for (int i=y2; i>y1; i--) {
                map[x1][i] = map[x1][i-1];
                MIN = Math.min(MIN, map[x1][i]);
            }
            for (int i=x1; i<x2; i++) {
                map[i][y1] = map[i+1][y1];
                MIN = Math.min(MIN, map[i][y1]);
            }
            for (int i=y1; i<y2; i++) {
                map[x2][i] = map[x2][i+1];
                MIN = Math.min(MIN, map[x2][i]);
            }
            for (int i=x2; i>x1; i--) {
                map[i][y2] = map[i-1][y2];
                MIN = Math.min(MIN, map[i][y2]);
            }
            
            map[x1+1][y2] = prev;
            
            answer[q] = MIN;
        }
        
        return answer;
    }
}