
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {

    static int N, M;
    static int[][] map;
    static final int INF = 1_000_000_000;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        M = Integer.parseInt(sp[0]);
        N = Integer.parseInt(sp[1]);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            sp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(sp[j]);
            }
        }

        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        // 0-1 bfs
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0, 0});

        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();

            if (cur[0] == N - 1 && cur[1] == M - 1) {
                System.out.println(dp[N - 1][M - 1]);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    if (dp[nx][ny] > dp[cur[0]][cur[1]]) {
                        dp[nx][ny] = dp[cur[0]][cur[1]];
                        deque.addFirst(new int[]{nx, ny});
                    }
                } else {
                    if (dp[nx][ny] > dp[cur[0]][cur[1]] + 1) {
                        dp[nx][ny] = dp[cur[0]][cur[1]] + 1;
                        deque.addLast(new int[]{nx, ny});
                    }
                }
            }
        }

    }
}
