
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if ((nx >= 0 && nx < rows && ny >= 0 && ny < cols) && !visited[nx][ny] && map[nx][ny] > 0) {
                dfs(nx, ny);
            }
        }
    }

    static int check() {
        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    cnt++;
                    if (cnt == 2) {
                        return 2;
                    }
                    dfs(i, j);
                }
            }
        }

        return cnt == 0 ? 0 : 1;
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int rows, cols;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        rows = Integer.parseInt(sp[0]);
        cols = Integer.parseInt(sp[1]);
        map = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            sp = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                map[i][j] = Integer.parseInt(sp[j]);
            }
        }

        int year = 0;

        int[][] temp = new int[rows][cols];

        while (true) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int sea = 0;
                    if (map[i][j] > 0) {
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k], ny = j + dy[k];
                            if ((nx >= 0 && nx < rows && ny >= 0 && ny < cols) && map[nx][ny] == 0) {
                                sea++;
                            }
                        }
                    }
                    temp[i][j] = Math.max(0, map[i][j] - sea);
                }
            }

            for (int i = 0; i < rows; i++) {
                map[i] = temp[i].clone();
            }

            year++;

            visited = new boolean[rows][cols];
            int flag = check();
            if (flag == 0) {
                System.out.println(0);
                break;
            } else if (flag == 2) {
                System.out.println(year);
                break;
            }
        }
    }
}
