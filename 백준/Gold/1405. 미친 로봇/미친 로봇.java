
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static double dfs(int x, int y, int cnt, double probability) {
        if (cnt == N) {
            return probability;
        }

        double sum = 0.0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                sum += dfs(nx, ny, cnt + 1, probability * percent[i]);
                visited[nx][ny] = false;
            }
        }

        return sum;
    }


    static int N;
    static double[] percent = new double[4];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            percent[i] = Double.parseDouble(st.nextToken()) / 100;
        }

        visited = new boolean[2 * N + 1][2 * N + 1];
        visited[N][N] = true;

        double answer = dfs(N, N, 0, 1.0);
        System.out.println(answer);
    }
}
