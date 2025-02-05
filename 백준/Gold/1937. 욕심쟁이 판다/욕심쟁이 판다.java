import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long DFS(int x, int y) {

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 1; // 자신 칸으로 초기화

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!(1 <= nx && nx <= n && 1 <= ny && ny <= n)) {
                continue;
            }

            if (map[x][y] < map[nx][ny]) {
                // 이동할수있는 칸의 메모값+1
                dp[x][y] = Math.max(dp[x][y], DFS(nx, ny) + 1);
            }
        }

        return dp[x][y];
    }

    static int n;
    static int[][] map;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[i][j]: (i,j)에서 이동할수있는 최대한 많은칸
        dp = new long[n + 1][n + 1];

        // -1로 초기화 (방문 여부)
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        long MAX = -1L;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = DFS(i, j);
                MAX = Math.max(MAX, dp[i][j]);
            }
        }

        System.out.println(MAX);
    }
}
