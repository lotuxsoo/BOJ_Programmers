
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int count() {
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    cnt += map[i][j];
                }
            }
        }
        return cnt;
    }

    static void bfs() {
        int[][] copied = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            copied[i] = map[i].clone();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    int dust = map[i][j] / 5;
                    int count = 0;

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (!(0 <= nx && nx < R && 0 <= ny && ny < C) || map[nx][ny] == -1) {
                            continue;
                        }
                        count++;
                        copied[nx][ny] += dust;
                    }
                    copied[i][j] -= count * dust;
                }
            }
        }

        map = copied;
    }

    static void purify() {
        int top = purifier.get(0)[0];
        int bottom = purifier.get(1)[0];

        // 위쪽 공기청정기 (반시계)
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < top; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[top][i] = map[top][i - 1];
        }
        map[top][1] = 0;

        // 아래쪽 공기청정기 (시계)
        for (int i = bottom + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        for (int i = R - 1; i > bottom; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[bottom][i] = map[bottom][i - 1];
        }
        map[bottom][1] = 0;
    }

    static int R, C, T;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<int[]> purifier = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    purifier.add(new int[]{i, j});
                }
            }
        }

        while (T-- > 0) {
            // 1. 미세먼지 확산
            bfs();
            // 2. 공기청정기 작동
            purify();
        }
        System.out.println(count());
    }
}
