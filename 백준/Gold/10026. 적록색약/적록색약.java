
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static void dfs(int x, int y, char[][] map) {
        char color = map[x][y];
        map[x][y] = 'X';

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if ((0 <= nx && nx < N && 0 <= ny && ny < N) && (map[nx][ny] != 'X') && (color == map[nx][ny])) {
                dfs(nx, ny, map);
            }
        }
    }

    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        char[][] blindMap = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                blindMap[i][j] = map[i][j] == 'G' ? 'R' : map[i][j];
            }
        }

        int normalCount = 0;
        int blindCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 'X') {
                    dfs(i, j, map);
                    normalCount++;
                }
                if (blindMap[i][j] != 'X') {
                    dfs(i, j, blindMap);
                    blindCount++;
                }
            }
        }

        System.out.println(normalCount + " " + blindCount);
    }
}
