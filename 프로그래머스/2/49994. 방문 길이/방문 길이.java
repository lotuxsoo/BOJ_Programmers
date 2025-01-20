import java.util.*;

class Solution {

    static boolean cango(int x, int y) {
        return 0 <= x && x <= 10 && 0 <= y && y <= 10;
    }
    
    public int solution(String dirs) {
        int answer = 0;
        
        int[] now = new int[]{5,5};
        Set<String> set = new HashSet<>();
        
        for (String dir : dirs.split("")) {
            String s1 = now[0] + " " + now[1];
            StringBuilder sb = new StringBuilder(now[0]).append(" ").append(now[1]);
            if (dir.equals("U")) {
                int nx = now[0]-1;
                int ny = now[1];
                if (cango(nx, ny)) {
                    s1 = s1 + " " + nx + " " + ny;
                    String s2 = nx + " " + ny + " " + now[0] + " " + now[1];
                    set.add(s1);
                    set.add(s2);
                    now[0] = nx;
                }
            } else if (dir.equals("D")) {
                int nx = now[0]+1;
                int ny = now[1];
                if (cango(nx, ny)) {
                    s1 = s1 + " " + nx + " " + ny;
                    String s2 = nx + " " + ny + " " + now[0] + " " + now[1];
                    set.add(s1);
                    set.add(s2);
                    now[0] = nx;
                }
            } else if (dir.equals("R")) {
                int nx = now[0];
                int ny = now[1]+1;
                if (cango(nx, ny)) {
                    s1 = s1 + " " + nx + " " + ny;
                    String s2 = nx + " " + ny + " " + now[0] + " " + now[1];
                    set.add(s1);
                    set.add(s2);
                    now[1] = ny;
                }
            } else if (dir.equals("L")) {
                int nx = now[0];
                int ny = now[1]-1;
                if (cango(nx, ny)) {
                    s1 = s1 + " " + nx + " " + ny;
                    String s2 = nx + " " + ny + " " + now[0] + " " + now[1];
                    set.add(s1);
                    set.add(s2);
                    now[1] = ny;
                }
            }
        }
        
        answer = set.size() / 2;
        
        return answer;
    }
}