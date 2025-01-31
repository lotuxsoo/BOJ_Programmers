class Solution {
    
    static boolean isValid(int x, int y, int n, int m) {
        return -n <= x && x <= n && -m <= y && y <= m;
    }
    
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = {};
        
        int n = board[0] / 2;
        int m = board[1] / 2;
        
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,1,-1};
        
        int cx = 0, cy = 0;
        
        for (String s : keyinput) {
            int nx = cx, ny = cy;
            if (s.equals("left")) {
                nx = cx + dx[0];
                ny = cy + dy[0];
            } else if (s.equals("right")) {
                nx = cx + dx[1];
                ny = cy + dy[1];
            } else if (s.equals("up")) {
                nx = cx + dx[2];
                ny = cy + dy[2];
            } else if (s.equals("down")) {
                nx = cx + dx[3];
                ny = cy + dy[3];
            }
            if (isValid(nx, ny, n, m)) {
                cx = nx;
                cy = ny;
            }
        }
        
        answer = new int[]{cx,cy};
        
        return answer;
    }
}