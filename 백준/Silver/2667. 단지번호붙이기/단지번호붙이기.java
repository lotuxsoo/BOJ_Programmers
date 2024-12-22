import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int n;
    static int[][] grid;
    static boolean[][] visited;
    static int num;

    static int DFS(int x, int y) {
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (canGo(newX, newY, grid[x][y])) {
                visited[newX][newY] = true;
                num++;
                DFS(newX, newY);
            }
        }

        return num;
    }

    static boolean canGo(int x, int y, int value) {
        if (!(0 <= x && x < n && 0 <= y && y < n)) {
            return false;
        }
        if (visited[x][y]) {
            return false;
        }
        if (grid[x][y] != value) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(split[j]);
            }
        }

        int numOfDfs = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    numOfDfs++;
                    num = 1;
                    list.add(DFS(i, j));
                }
            }
        }

        System.out.println(numOfDfs);

        list.stream().sorted().forEach(System.out::println);
    }
}