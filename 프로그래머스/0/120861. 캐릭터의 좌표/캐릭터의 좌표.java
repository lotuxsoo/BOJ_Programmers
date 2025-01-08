import java.util.*;

class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = {};
        
        HashMap<String, int[]> map = new HashMap<>();
        map.put("up", new int[]{0,1});
        map.put("down", new int[]{0,-1});
        map.put("left", new int[]{-1,0});
        map.put("right", new int[]{1,0});
        
        int[] now = {0,0}; // 가로,세로
        
        for (String key : keyinput) {
            if (map.containsKey(key)) {
                int[] dx = map.get(key);
                int nx = now[0] + dx[0];
                int ny = now[1] + dx[1];
                if (Math.abs(nx) <= board[0]/2 && Math.abs(ny) <= board[1]/2) {
                    now[0] = nx;
                    now[1] = ny;
                }
            }
        }
        
        answer = now;
        
        return answer;
    }
}