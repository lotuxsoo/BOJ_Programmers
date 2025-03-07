
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static void dfs(int x, int y, int flag) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (flag == 0) {
                if ((0 <= nx && nx < N && 0 <= ny && ny < N) && !visited[nx][ny] && map[x][y] == map[nx][ny]) {
                    dfs(nx, ny, flag);
                }
            } else {
                if (!(0 <= nx && nx < N && 0 <= ny && ny < N) || visited[nx][ny]) {
                    continue;
                }
                if (map[x][y] == 'R' || map[x][y] == 'G') {
                    if (map[nx][ny] == 'R' || map[nx][ny] == 'G') {
                        dfs(nx, ny, flag);
                    }
                } else if (map[x][y] == 'B' && map[nx][ny] == 'B') {
                    dfs(nx, ny, flag);
                }
            }
        }
    }

    static int N;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int normalCount = 0, abnormalCount = 0;

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, 0);
                    normalCount++;
                }
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, 1);
                    abnormalCount++;
                }
            }
        }

        System.out.println(normalCount + " " + abnormalCount);
    }
}
