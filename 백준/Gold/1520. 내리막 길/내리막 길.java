import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int DFS(int x, int y) {
        if (x == M && y == N) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0; // (경로가 없을수도 아닐수도, 이후 누적)

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!(1 <= nx && nx <= M && 1 <= ny && ny <= N)) {
                continue;
            }

            if (map[x][y] > map[nx][ny]) {
                dp[x][y] += DFS(nx, ny);
            }
        }

        return dp[x][y];
    }

    static int M, N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[i][j]: (i,j)~(M,N)까지 갈수있는 경로의 개수
        dp = new int[M + 1][N + 1];

        // 필수 **미방문 표시**
        for (int i = 0; i < M + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        int H = DFS(1, 1);
        System.out.println(H);
    }
}
