
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int x, y, time;

        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

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

        Queue<Point> queue = new LinkedList<>();
        Queue<Point> fireQueue = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    queue.add(new Point(i, j, 0));
                } else if (map[i][j] == 'F') {
                    fireQueue.add(new Point(i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {

            int size = fireQueue.size();
            while (size-- > 0) {
                Point cur = fireQueue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i], ny = cur.y + dy[i];
                    if (!(0 <= nx && nx < R && 0 <= ny && ny < C) || map[nx][ny] == '#' || map[nx][ny] == 'F') {
                        continue;
                    }

                    fireQueue.add(new Point(nx, ny, cur.time + 1));
                    map[nx][ny] = 'F';
                }
            }

            size = queue.size();
            while (size-- > 0) {
                Point cur = queue.poll();

                if (cur.x == 0 || cur.x == R - 1 || cur.y == 0 || cur.y == C - 1) {
                    System.out.println(cur.time + 1);
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i], ny = cur.y + dy[i];
                    if (!(0 <= nx && nx < R && 0 <= ny && ny < C) || map[nx][ny] == '#' || map[nx][ny] == 'F'
                            || map[nx][ny] == 'J') {
                        continue;
                    }

                    queue.add(new Point(nx, ny, cur.time + 1));
                    map[nx][ny] = 'J';
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}
