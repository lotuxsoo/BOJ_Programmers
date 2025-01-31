class Solution {
    public int[] solution(int brown, int yellow) {
        for (int h = 1; h <= Math.sqrt(yellow); h++) { // 세로 길이는 yellow의 약수
            if (yellow % h == 0) {
                int w = yellow / h; // 가로 길이
                
                // 갈색 개수 확인
                if (2 * (w + h) + 4 == brown) {
                    return new int[]{w + 2, h + 2}; // 전체 가로, 세로 크기 반환
                }
            }
        }
        return new int[]{}; // 예외 케이스 (실제 문제에서는 없음)
    }
}