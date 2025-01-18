import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x;
        int y;
        int z; // 안부숨:0, 부숨:1

        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] A = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] splits = br.readLine().split("");
            for (int j = 0; j < splits.length; j++) {
                A[i][j] = Integer.parseInt(splits[j]);
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0));

        int[][][] dp = new int[N][M][2]; // 거리저장
        for (int[][] dp1 : dp) {
            for (int[] dp2 : dp1) {
                Arrays.fill(dp2, -1);
            }
        }
        dp[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int x = now.x, y = now.y, z = now.z;

            if (x == N - 1 && y == M - 1) {
                System.out.println(dp[x][y][z]);
                return;
            }

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                    continue;
                }
                if (A[nx][ny] == 0 && dp[nx][ny][z] == -1) {
                    dp[nx][ny][z] = dp[x][y][z] + 1;
                    queue.add(new Node(nx, ny, z));
                } else if (A[nx][ny] == 1 && z == 0 && dp[nx][ny][1] == -1) {
                    dp[nx][ny][1] = dp[x][y][z] + 1;
                    queue.add(new Node(nx, ny, 1));
                }
            }
        }
        System.out.println(-1);
    }
}
