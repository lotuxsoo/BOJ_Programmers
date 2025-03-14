
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> floodQueue = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'S') {
                    queue.add(new int[]{i, j});
                } else if (map[i][j] == '*') {
                    floodQueue.add(new int[]{i, j});
                }
            }
        }

        int time = 1;
        while (!queue.isEmpty()) {

            int size = floodQueue.size();
            while (size-- > 0) {
                int[] cur = floodQueue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                    if (!(0 <= nx && nx < R && 0 <= ny && ny < C) || map[nx][ny] == 'X' || map[nx][ny] == '*'
                            || map[nx][ny] == 'D') {
                        continue;
                    }

                    floodQueue.add(new int[]{nx, ny});
                    map[nx][ny] = '*';
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == '*') {
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k], ny = j + dy[k];
                            if (!(0 <= nx && nx < R && 0 <= ny && ny < C) || map[nx][ny] == 'X'
                                    || map[nx][ny] == 'D') {
                                continue;
                            }
                            map[i][j] = '+';
                        }
                    }
                }
            }

            size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                    if (!(0 <= nx && nx < R && 0 <= ny && ny < C) || map[nx][ny] == 'X' || map[nx][ny] == '*'
                            || map[nx][ny] == '+' || map[nx][ny] == 'S') {
                        continue;
                    }

                    if (map[nx][ny] == 'D') {
                        System.out.println(time);
                        return;
                    }

                    queue.add(new int[]{nx, ny});
                    map[nx][ny] = 'S';
                }
            }

            time++;
        }

        System.out.println("KAKTUS");
    }
}
