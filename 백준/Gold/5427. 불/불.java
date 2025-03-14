
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    static boolean bfs() {
        while (!queue.isEmpty()) {
            // 불 먼저 이동
            int size = fireQueue.size();
            while (size-- > 0) {
                Point cur = fireQueue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i], ny = cur.y + dy[i];
                    if (!(0 <= nx && nx < h && 0 <= ny && ny < w) || map[nx][ny] == '#' || map[nx][ny] == '*') {
                        continue;
                    }
                    map[nx][ny] = '*';
                    fireQueue.add(new Point(nx, ny, cur.time + 1));
                }
            }

            // 상근 이동
            size = queue.size();
            while (size-- > 0) {
                Point cur = queue.poll();

                if (cur.x == 0 || cur.x == h - 1 || cur.y == 0 || cur.y == w - 1) {
                    sb.append(cur.time + 1 + "\n");
                    return true;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i], ny = cur.y + dy[i];
                    if (!(0 <= nx && nx < h && 0 <= ny && ny < w) || map[nx][ny] == '#' || map[nx][ny] == '*'
                            || map[nx][ny] == '@') {
                        continue;
                    }
                    map[nx][ny] = '@';
                    queue.add(new Point(nx, ny, cur.time + 1));
                }
            }
        }

        return false;
    }

    static int w, h;
    static char[][] map;
    static Queue<Point> queue;
    static Queue<Point> fireQueue;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            queue = new LinkedList<>();
            fireQueue = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '*') {
                        fireQueue.add(new Point(i, j, 0));
                    } else if (map[i][j] == '@') {
                        queue.add(new Point(i, j, 0));
                    }
                }
            }

            if (!bfs()) {
                sb.append("IMPOSSIBLE\n");
            }
        }
        System.out.println(sb.toString());
    }
}
