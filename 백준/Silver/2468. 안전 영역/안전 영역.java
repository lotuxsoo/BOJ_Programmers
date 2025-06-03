
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static void dfs(int x, int y, int height) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if ((nx >= 0 && nx < N && ny >= 0 && ny < N) && map[nx][ny] > height && !visited[nx][ny]) {
                dfs(nx, ny, height);
            }
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static int[][] map;
    static int minHeight = 1_000_000_000;
    static int maxHeight = 0;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sp[j]);
                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int answer = 1; // 아무 지역도 안잠길때

        for (int height = minHeight; height < maxHeight; height++) {
            visited = new boolean[N][N]; // height마다 초기화
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > height) {
                        dfs(i, j, height);
                        cnt++;
                    }
                }
            }

            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}
