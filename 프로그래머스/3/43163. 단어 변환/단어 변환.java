import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // target이 words에 없는 경우 바로 0 반환
        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }

        // BFS를 위한 큐와 방문 배열 초기화
        Queue<String> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        queue.offer(begin);

        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size(); // 현재 레벨의 노드 개수
            step++; // 새로운 레벨로 진입했으므로 단계 증가

            // 현재 레벨의 모든 노드를 처리
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // 변환 가능한 단어를 찾아 큐에 추가
                for (int j = 0; j < words.length; j++) {
                    if (!visited[j] && canTransform(current, words[j])) {
                        if (words[j].equals(target)) {
                            return step; // target에 도달하면 변환 단계 반환
                        }
                        queue.offer(words[j]);
                        visited[j] = true; // 방문 체크
                    }
                }
            }
        }

        return 0; // 변환 불가능한 경우
    }

    // 두 단어가 정확히 한 글자만 다른지 확인하는 함수
    private boolean canTransform(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false; // 두 글자 이상 다르면 변환 불가
                }
            }
        }
        return diff == 1; // 정확히 한 글자만 다르면 true
    }
}
