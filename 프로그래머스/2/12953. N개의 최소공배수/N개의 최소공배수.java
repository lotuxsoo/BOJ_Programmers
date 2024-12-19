class Solution {
    public static int lcm(int a, int b) {
        return Math.abs(a*b) / gcd(a,b);
    }
    
    public static int gcd(int a, int b) {
        while (b != 0) {
            int mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }
    
    public int solution(int[] arr) {
        int answer = 0;
        
        int now = 0; // 0~arr.length-1
        int temp = arr[now];
        while (now < arr.length) {
            temp = lcm(temp, arr[now]);
            now++;
        }
        
        answer = temp;
        
        return answer;
    }
}