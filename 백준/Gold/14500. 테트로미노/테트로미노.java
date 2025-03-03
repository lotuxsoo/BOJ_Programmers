
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void backtrack(int x, int y, int depth, int sum) {
        if (depth == 3) {
            MAX_VAL = Math.max(MAX_VAL, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if ((0 <= nx && nx < N && 0 <= ny && ny < M) && !visited[nx][ny]) {
                if (depth == 1) {
                    visited[nx][ny] = true;
                    backtrack(x, y, depth + 1, sum + map[nx][ny]);
                    visited[nx][ny] = false;
                }
                visited[nx][ny] = true;
                backtrack(nx, ny, depth + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] tx = {0, 0, 1};
    static int[] ty = {-1, 1, 0};
    static int MAX_VAL = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                backtrack(i, j, 0, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(MAX_VAL);
    }
}
