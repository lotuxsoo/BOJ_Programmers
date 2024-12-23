import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int w, h;
    static int[][] grid;
    static boolean[][] visited;

    static void DFS(int x, int y) {
        int[] dx = new int[]{-1, 1, 0, 0, 1, -1, 1, -1};
        int[] dy = new int[]{0, 0, -1, 1, 1, -1, -1, 1};

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (canGo(newX, newY)) {
                visited[newX][newY] = true;
                DFS(newX, newY);
            }
        }
    }

    static boolean canGo(int x, int y) {
        if (!(0 <= x && x < h && 0 <= y && y < w)) {
            return false;
        }
        if (visited[x][y] || grid[x][y] == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                break;
            }
            grid = new int[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int num = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visited[i][j] && grid[i][j] == 1) {
                        visited[i][j] = true;
                        DFS(i, j);
                        num++;
                    }
                }
            }
            System.out.println(num);
        }
    }
}
