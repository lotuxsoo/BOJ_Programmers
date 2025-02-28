
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void initFlood(int height) {
        floodMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] <= height) {
                    floodMap[i][j] = FLOOD;
                }
            }
        }
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
                continue;
            }
            if (floodMap[nx][ny] != FLOOD && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    static final int FLOOD = -1;
    static int N;
    static int[][] map;
    static int[][] floodMap;
    static int MAX_HEIGHT = 0;
    static int ANS = 0;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                MAX_HEIGHT = Math.max(MAX_HEIGHT, map[i][j]);
            }
        }

        while (MAX_HEIGHT-- > 0) {
            int count = 0;
            initFlood(MAX_HEIGHT);

            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (floodMap[i][j] != FLOOD && !visited[i][j]) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            ANS = Math.max(ANS, count);
        }

        System.out.println(ANS);
    }
}
