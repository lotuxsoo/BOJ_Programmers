
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean isEdge(int x, int y) {
        if (x == 0 || y == 0 || x == R - 1 || y == C - 1) {
            return true;
        }
        return false;
    }

    static char[][] map;
    static int R, C;
    static final char WALL = '#';
    static final char AIR = '.';
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> fQueue = new LinkedList<>();
    static Queue<int[]> jQueue = new LinkedList<>();
    static int[][] fVisited;
    static int[][] jVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fVisited = new int[R][C];
        jVisited = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(fVisited[i], -1);
            Arrays.fill(jVisited[i], -1);
        }

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    jQueue.add(new int[]{i, j});
                    jVisited[i][j] = 0;
                } else if (map[i][j] == 'F') {
                    fQueue.add(new int[]{i, j});
                    fVisited[i][j] = 0;
                }
            }
        }

        // fire bfs
        while (!fQueue.isEmpty()) {
            int[] cur = fQueue.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!(0 <= nx && nx < R && 0 <= ny && ny < C)) {
                    continue;
                }
                if (map[nx][ny] != WALL && fVisited[nx][ny] == -1) {
                    fVisited[nx][ny] = fVisited[x][y] + 1;
                    fQueue.add(new int[]{nx, ny});
                }
            }
        }

        // jihoon bfs
        while (!jQueue.isEmpty()) {
            int[] cur = jQueue.poll();
            int x = cur[0], y = cur[1];

            if (isEdge(x, y)) {
                System.out.println(jVisited[x][y] + 1);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!(0 <= nx && nx < R && 0 <= ny && ny < C)) {
                    continue;
                }
                if (map[nx][ny] != WALL && jVisited[nx][ny] == -1) {
                    if (fVisited[nx][ny] == -1 || fVisited[nx][ny] > jVisited[x][y] + 1) {
                        jVisited[nx][ny] = jVisited[x][y] + 1;
                        jQueue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}