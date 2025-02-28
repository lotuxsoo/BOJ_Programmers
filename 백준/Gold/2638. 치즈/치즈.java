
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static void initialize() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        map[0][0] = OUTER_AIR;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                    continue;
                }
                if (visited[nx][ny] || map[nx][ny] == CHEESE) {
                    continue;
                }

                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                map[nx][ny] = OUTER_AIR;
            }
        }
    }

    static boolean canMelt(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                continue;
            }
            if (map[nx][ny] == OUTER_AIR) {
                cnt++;
            }
        }
        return cnt >= 2;
    }

    static int N, M;
    static int[][] map;
    static final int CHEESE = 1;
    static final int OUTER_AIR = -1;
    static final int INNER_AIR = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int hour = 0;
        while (true) {
            initialize();
            int meltCount = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == CHEESE && canMelt(i, j)) {
                        map[i][j] = INNER_AIR;
                        meltCount++;
                    }
                }
            }

            if (meltCount == 0) {
                break;
            }
            hour++;
        }

        System.out.println(hour);
    }
}
