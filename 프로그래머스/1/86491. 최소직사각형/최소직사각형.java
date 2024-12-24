import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        TreeSet<Integer> widths = new TreeSet<>(Collections.reverseOrder());
        TreeSet<Integer> heights = new TreeSet<>(Collections.reverseOrder());
        
        for (int[] size : sizes) {
            int width = Math.max(size[0], size[1]);
            int height = Math.min(size[0], size[1]);
            widths.add(width);
            heights.add(height);
        }
        
        answer = widths.first() * heights.first();
        
        return answer;
    }
}