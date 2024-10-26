class Solution {
    static int answer;
    static int length;
    static int targetNum;
    static int[] numbersArr;
        
    static void DFS(int d, int sum) {
        if (d == length) {
            if (sum == targetNum) answer++;
            return;
        }
        
        DFS(d+1, sum + numbersArr[d]);
        DFS(d+1, sum - numbersArr[d]);
    }
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        length = numbers.length;
        targetNum = target;
        numbersArr = numbers;
    
        DFS(0,0);
        return answer;
    }
}