class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        int maxRow = Integer.MIN_VALUE;
        int maxCol = Integer.MIN_VALUE;
        
        for (int[] size : sizes) {
            int row = Math.max(size[0], size[1]);
            int col = Math.min(size[0], size[1]);
            maxRow = Math.max(maxRow, row);
            maxCol = Math.max(maxCol, col);
        }
        
        answer = maxRow * maxCol;
        
        return answer;
    }
}