import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class State {
        int x, y, cnt;

        State(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int N, M, K;
    static int[][] map;
    static int[][][] dp;
    static final int INF = 1_000_000_000;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 1; j <= M; j++) {
                map[i][j] = ch[j - 1] - '0';
            }
        }

        // (x,y)에서 k번까지 부쉈을때의 최단거리
        dp = new int[N + 1][M + 1][K + 1];

        // INF로 초기화
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        // 초기값
        dp[1][1][0] = 0;
        int MIN_DIST = INF;

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(1, 1, 0));

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.x == N && cur.y == M) {
                MIN_DIST = Math.min(MIN_DIST, dp[cur.x][cur.y][cur.cnt] + 1);
                continue;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(1 <= nx && nx <= N && 1 <= ny && ny <= M)) {
                    continue;
                }

                if (map[nx][ny] == 0) {
                    if (dp[nx][ny][cur.cnt] > dp[cur.x][cur.y][cur.cnt] + 1) {
                        dp[nx][ny][cur.cnt] = dp[cur.x][cur.y][cur.cnt] + 1;
                        queue.add(new State(nx, ny, cur.cnt));
                    }
                } else if ((map[nx][ny] == 1) && cur.cnt + 1 <= K) {
                    if (dp[nx][ny][cur.cnt + 1] > dp[cur.x][cur.y][cur.cnt] + 1) {
                        dp[nx][ny][cur.cnt + 1] = dp[cur.x][cur.y][cur.cnt] + 1;
                        queue.add(new State(nx, ny, cur.cnt + 1));
                    }
                }
            }
        }

        System.out.println(MIN_DIST == INF ? -1 : MIN_DIST);
    }
}
