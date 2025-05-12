
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static void dfs(int x, int y, int n, int sum) {
        if (n == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (!(0 <= nx && nx < N && 0 <= ny && ny < M) || visited[nx][ny]) {
                continue;
            }

            if (n == 2) {
                visited[nx][ny] = true;
                dfs(x, y, n + 1, sum + arr[nx][ny]);
                visited[nx][ny] = false;
            }

            visited[nx][ny] = true;
            dfs(nx, ny, n + 1, sum + arr[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    static int maxSum = 0;
    static int N, M;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            sp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(sp[j]);
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, arr[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(maxSum);
    }
}
