
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
    static class State {
        int x, y, keyMask;

        State(int x, int y, int keyMask) {
            this.x = x;
            this.y = y;
            this.keyMask = keyMask;
        }
    }

    static int N, M;
    static char[][] map;
    static int[] start;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = chars[j];
                if (map[i][j] == '0') {
                    start = new int[]{i, j};
                }
            }
        }

        // x,y,keyMask
        int[][][] dp = new int[N][M][65];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(start[0], start[1], 0));
        dp[start[0]][start[1]][0] = 0; // 시작점 처리ㅣ

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (map[cur.x][cur.y] == '1') {
                System.out.println(dp[cur.x][cur.y][cur.keyMask]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i], ny = cur.y + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                    continue;
                }

                if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
                    // 비트 키기
                    int newKeyMask = cur.keyMask | (1 << map[nx][ny] - 'a');
                    if (dp[nx][ny][newKeyMask] > dp[cur.x][cur.y][cur.keyMask] + 1) {
                        dp[nx][ny][newKeyMask] = dp[cur.x][cur.y][cur.keyMask] + 1;
                        queue.add(new State(nx, ny, newKeyMask));
                    }
                } else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
                    // 비트 포함여부 확인
                    if ((cur.keyMask & (1 << map[nx][ny] - 'A')) != 0) {
                        if (dp[nx][ny][cur.keyMask] > dp[cur.x][cur.y][cur.keyMask] + 1) {
                            dp[nx][ny][cur.keyMask] = dp[cur.x][cur.y][cur.keyMask] + 1;
                            queue.add(new State(nx, ny, cur.keyMask));
                        }
                    }
                } else {
                    if (map[nx][ny] == '#') {
                        continue;
                    }

                    if (dp[nx][ny][cur.keyMask] > dp[cur.x][cur.y][cur.keyMask] + 1) {
                        dp[nx][ny][cur.keyMask] = dp[cur.x][cur.y][cur.keyMask] + 1;
                        queue.add(new State(nx, ny, cur.keyMask));
                    }
                }
            }
        }
        System.out.println(-1);
    }
}