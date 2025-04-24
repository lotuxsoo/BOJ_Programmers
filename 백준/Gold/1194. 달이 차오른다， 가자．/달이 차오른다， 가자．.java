
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N, M;
    static char[][] map;
    static int[] start;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

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

        boolean[][][] visited = new boolean[N][M][64];
        visited[start[0]][start[1]][0] = true; // 시작점 방문 체크

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 0, 0}); // x,y좌표,비트마스킹,이동횟수

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (map[cur[0]][cur[1]] == '1') {
                System.out.println(cur[3]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < M) || map[nx][ny] == '#') {
                    continue;
                }

                if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
                    int bitmask = cur[2] | (1 << (map[nx][ny] - 'a'));
                    if (!visited[nx][ny][bitmask]) {
                        visited[nx][ny][bitmask] = true;
                        queue.add(new int[]{nx, ny, bitmask, cur[3] + 1});
                    }
                    continue;
                }

                if (visited[nx][ny][cur[2]]) {
                    continue;
                }

                if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
                    if ((cur[2] & (1 << (map[nx][ny] - 'A'))) != 0) {
                        visited[nx][ny][cur[2]] = true;
                        queue.add(new int[]{nx, ny, cur[2], cur[3] + 1});
                    }
                    continue;
                }

                visited[nx][ny][cur[2]] = true;
                queue.add(new int[]{nx, ny, cur[2], cur[3] + 1});
            }
        }

        System.out.println(-1);
    }
}
