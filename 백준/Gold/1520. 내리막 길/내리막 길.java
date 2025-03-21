
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int dfs(int x, int y, int cost) {
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        dp[x][y] = 0; // 초기화

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if ((0 <= nx && nx < M && 0 <= ny && ny < N) && cost > map[nx][ny]) {
                dp[x][y] += dfs(nx, ny, map[nx][ny]);
            }
        }

        return dp[x][y];
    }

    static int M, N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[i][j]: (i,j)~(M-1,N-1)까지의 경로 가짓수
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(0, 0, map[0][0]));
    }
}
