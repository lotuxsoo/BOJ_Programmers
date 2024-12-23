import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] grid;
    static boolean[][] visited;

    static void DFS(int a, int b, int x) {
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newX = a + dx[i];
            int newY = b + dy[i];
            if (canGo(newX, newY, x)) {
                visited[newX][newY] = true;
                DFS(newX, newY, x);
            }
        }
    }

    static boolean canGo(int a, int b, int x) {
        if (!(0 <= a && a < n && 0 <= b && b < n)) {
            return false;
        }
        if (visited[a][b] || grid[a][b] <= x) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        visited = new boolean[n][n];
        int max = -1;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, grid[i][j]);
            }
        }

        int answer = 0;

        for (int x = 0; x <= max; x++) {
            int num = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && grid[i][j] > x) {
                        visited[i][j] = true;
                        DFS(i, j, x);
                        num++;
                    }
                }
            }
            answer = Math.max(answer, num);
            visited = new boolean[n][n];
        }

        System.out.println(answer);
    }
}
