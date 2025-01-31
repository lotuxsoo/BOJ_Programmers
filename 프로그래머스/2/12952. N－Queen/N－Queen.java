class Solution {
    
    static boolean isValid(int depth, int i) {
        for (int k=0; k<depth; k++) {
            if (col[k] == i) return false; // 같은 열 금지
            if (Math.abs(depth-k) == Math.abs(i-col[k])) return false;
        }
        return true;
    }
        
    static void backtrack(int depth, int n) {
        if (depth == n) {
            cnt++;
            return;
        }
        
        for (int i=0; i<n; i++) {
            if (isValid(depth, i)) {
                col[depth] = i;
                backtrack(depth+1, n);
            }
        }
    }
    
    static int cnt = 0;
    static int[] col;
    
    public int solution(int n) {
        int answer = 0;
        
        // col[i] = j: i행 j열에 퀸 위치
        col = new int[n];
        
        backtrack(0, n);
        
        answer = cnt;
        
        return answer;
    }
}