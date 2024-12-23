import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int M, N, K;
    static int[][] grid;
    static boolean[][] visited;

    static void fill(int y1, int y2, int x1, int x2) {
        for (int i = y1; i < y2; i++) {
            for (int j = x1; j < x2; j++) {
                grid[i][j] = 1;
            }
        }
    }

    static int DFS(int x, int y, int num) {
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (canGo(newX, newY, grid[x][y])) {
                visited[newX][newY] = true;
                num = DFS(newX, newY, num + 1);
            }
        }
        return num;
    }

    static boolean canGo(int x, int y, int value) {
        if (!(0 <= x && x < M && 0 <= y && y < N)) {
            return false;
        }
        if (visited[x][y] || grid[x][y] != value) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        grid = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine()); // 0 2 3 3
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            fill(y1, y2, x1, x2);
        }

        int num = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && grid[i][j] == 0) {
                    visited[i][j] = true;
                    num++;
                    int ret = DFS(i, j, 1);
                    list.add(ret);
                }
            }
        }

        System.out.println(num);
        Collections.sort(list);
        for (int i : list) {
            System.out.print(i + " ");
        }
    }
}