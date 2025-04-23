
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        // 화면의 이모티콘 개수, 클립보드의 이모티콘 개수
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});

        boolean[][] checked = new boolean[2001][2001]; // BFS는 상태 중복 체크 필수임
        checked[1][0] = true;

        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                if (cur[0] == S) {
                    System.out.println(time);
                    return;
                }

                int temp = cur[1];
                // 화면 이모티콘 모두 복사해서 클립보드에 저장
                if (cur[0] > 0) {
                    cur[1] = cur[0]; // 클립보드 덮어쓰기
                    if (cur[0] + cur[1] < 2001 && !checked[cur[0]][cur[1]]) {
                        queue.add(new int[]{cur[0], cur[1]});
                        checked[cur[0]][cur[1]] = true;
                    }
                    cur[1] = temp;
                }

                // 클립보드의 모든 이모티콘 화면에 붙여넣기
                if (cur[1] > 0) {
                    cur[0] += cur[1];
                    if (cur[0] + cur[1] < 2001 && !checked[cur[0]][cur[1]]) {
                        queue.add(new int[]{cur[0], cur[1]});
                        checked[cur[0]][cur[1]] = true;
                    }
                    cur[0] -= cur[1];
                }

                if (cur[0] > 0) {
                    // 화면에 있는 이모티콘 중 하나 삭제
                    cur[0]--;
                    if (cur[0] + cur[1] < 2001 && !checked[cur[0]][cur[1]]) {
                        queue.add(new int[]{cur[0], cur[1]});
                        checked[cur[0]][cur[1]] = true;
                    }
                    cur[0]++;
                }
            }
            time++;
        }
    }
}
