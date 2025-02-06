import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class State {
        int x, y, z;

        State(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static int N, M; // 세로,가로
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
        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 1; j <= M; j++) {
                map[i][j] = ch[j - 1] - '0';
            }
        }

        // (x,y)까지 이동하는 최단 경로
        // 0(안부숨), 1(한개까지 부숨)
        dp = new int[N + 1][M + 1][2];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        // BFS 풀이
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(1, 1, 0));

        // dp 초기값 지정 필수
        dp[1][1][0] = 1;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            // 종료 조건 필수
            if (cur.x == N && cur.y == M) {
                System.out.println(dp[N][M][cur.z]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(1 <= nx && nx <= N && 1 <= ny && ny <= M)) {
                    continue;
                }

                if (map[nx][ny] == 0) {
                    if (dp[nx][ny][cur.z] > dp[cur.x][cur.y][cur.z] + 1) {
                        dp[nx][ny][cur.z] = dp[cur.x][cur.y][cur.z] + 1; // 상태 업데이트
                        queue.add(new State(nx, ny, cur.z)); // 그냥 이동
                    }
                } else if (map[nx][ny] == 1 && cur.z == 0) {
                    if (dp[nx][ny][1] > dp[cur.x][cur.y][0] + 1) {
                        dp[nx][ny][1] = dp[cur.x][cur.y][0] + 1; // 상태 업데이트
                        queue.add(new State(nx, ny, 1)); // 부수고 이동
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
