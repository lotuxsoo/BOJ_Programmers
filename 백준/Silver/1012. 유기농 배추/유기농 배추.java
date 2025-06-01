
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static void dfs(int x, int y) {
        map[x][y] = -1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                continue;
            }
            if (map[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }

    static int M, N, K;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] sp = br.readLine().split(" ");
            M = Integer.parseInt(sp[0]);
            N = Integer.parseInt(sp[1]);
            K = Integer.parseInt(sp[2]);

            map = new int[N][M];

            for (int i = 0; i < K; i++) {
                sp = br.readLine().split(" ");
                int x = Integer.parseInt(sp[0]);
                int y = Integer.parseInt(sp[1]);
                map[y][x] = 1;
            }

            int answer = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        answer++;
                        dfs(i, j);
                    }
                }
            }

            System.out.println(answer);
        }
    }
}
