import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static Queue<Pair> que = new ArrayDeque<>();

    static void BFS() {
        while (!que.isEmpty()) {
            Pair now = que.poll();
            int x = now.x, y = now.y;

            int[] dx = new int[]{-1, 1, 0, 0};
            int[] dy = new int[]{0, 0, -1, 1};

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (canGo(newX, newY)) {
                    visited[newX][newY] = true;
                    grid[newX][newY] = grid[x][y] + 1;
                    que.offer(new Pair(newX, newY));
                }
            }
        }
    }

    static boolean canGo(int x, int y) {
        if (!(0 <= x && x < N && 0 <= y && y < M)) {
            return false;
        }
        if (visited[x][y] || grid[x][y] == -1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    visited[i][j] = true;
                    que.offer(new Pair(i, j));
                } else {
                    flag = true;
                }
            }
        }

        if (!flag) {
            System.out.println(0);
            return;
        }

        BFS();

        int max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 0) {
                    System.out.println(-1);
                    return;
                } else {
                    max = Math.max(max, grid[i][j]);
                }
            }
        }

        System.out.println(max - 1);
    }
}
