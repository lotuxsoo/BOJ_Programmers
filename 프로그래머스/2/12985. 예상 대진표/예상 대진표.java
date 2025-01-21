class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;

        int[] tree = new int[n+1];
        
        int aIndex = a;
        int bIndex = b;
        
        while (true) {
            answer++;
            
            if (aIndex%2 != 0) {
                aIndex++;
            }
            if (bIndex%2 != 0) {
                bIndex++;
            }
            if (aIndex == bIndex) {
                break;
            }
            
            aIndex /= 2;
            bIndex /= 2;
        }
        

        return answer;
    }
}