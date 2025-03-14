
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static void moveWall() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j <= 7; j++) {
                if (map[i][j] == '#') {
                    map[i][j] = '.';
                    if (i != 7) {
                        map[i + 1][j] = '#';
                    }
                }
            }
        }
    }

    static char[][] map = new char[8][8];
    static int[] dx = {-1, 1, 0, 0, 1, -1, 1, -1, 0};
    static int[] dy = {0, 0, -1, 1, 1, -1, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{7, 0});

        while (!queue.isEmpty()) {
            boolean[][] visited = new boolean[8][8];

            // 욱제 이동
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();

                if (cur[0] == 0 && cur[1] == 7) {
                    System.out.println(1);
                    return;
                }

                if (map[cur[0]][cur[1]] == '#') {
                    continue; // 벽으로 바뀐칸으로 이동x
                }

                for (int i = 0; i < 9; i++) {
                    int nx = cur[0] + dx[i], ny = cur[1] + dy[i];

                    if (!(0 <= nx && nx < 8 && 0 <= ny && ny < 8) || visited[nx][ny] || map[nx][ny] == '#') {
                        continue;
                    }

                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }

            // 벽 이동
            moveWall();
        }

        System.out.println(0);
    }
}
