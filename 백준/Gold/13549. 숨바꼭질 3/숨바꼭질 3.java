
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    static int N, K;
    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        K = Integer.parseInt(sp[1]);

        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{N, 0});

        // 방문 여부 체크 필수
        boolean[] visited = new boolean[MAX + 1];

        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();

            if (cur[0] == K) {
                System.out.println(cur[1]);
                break;
            }

            // 해당 위치에 빨리 도착할때 체크
            if (visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;

            if (cur[0] - 1 >= 0 && !visited[cur[0] - 1]) {
                deque.addLast(new int[]{cur[0] - 1, cur[1] + 1});
            }
            if (cur[0] + 1 <= MAX && !visited[cur[0] + 1]) {
                deque.addLast(new int[]{cur[0] + 1, cur[1] + 1});
            }
            if (cur[0] * 2 <= MAX && !visited[cur[0] * 2]) {
                deque.addFirst(new int[]{cur[0] * 2, cur[1]});
            }
        }

    }
}
