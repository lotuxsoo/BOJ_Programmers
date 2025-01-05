class Solution {
    static String[] moeum = {"A","E","I","O","U"};
    static String word;
    static int count = 0;
    static boolean found = false;
    
    static void DFS(String s, int len) {
        if (found) return;
        
        count++;
        
        if (s.equals(word)) {
            found = true;
            return;
        }
        
        if (len == 5) {
            return;
        }
        
        for (int i=0; i<moeum.length; i++) {
            DFS(s + moeum[i], len + 1);
        }
    }
    
    public int solution(String word) {
        int answer = 0;
        this.word = word;
        
        for (int i=0; i<moeum.length; i++) {
            DFS(moeum[i], 1);
            
            if (found) break;
        }
        
        answer = count;
        
        return answer;
    }
}