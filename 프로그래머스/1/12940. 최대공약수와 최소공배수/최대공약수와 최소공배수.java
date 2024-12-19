class Solution {
    public static int gcd(int a, int b) {        
        while (b != 0) {
            int mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }
    
    public static int lcm(int a, int b) {
        int gcd = gcd(a,b);
        return Math.abs(a*b) / gcd;
    }
    
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        answer[0] = gcd(n,m);
        answer[1] = lcm(n,m);
        
        return answer;
    }
}