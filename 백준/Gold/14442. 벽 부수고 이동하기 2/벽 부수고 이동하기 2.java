import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x, y, z;

        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z; // 벽부순 횟수
        }
    }

    static int N, M, K;
    static int[][][] dp;
    static int[][] map;

    static int BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0));
        dp[0][0][0] = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y, z = cur.z;

            if (x == N - 1 && y == M - 1) {
                return dp[x][y][z];
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                    continue;
                }

                if (map[nx][ny] == 0) {
                    if (dp[nx][ny][z] > dp[x][y][z] + 1) {
                        dp[nx][ny][z] = dp[x][y][z] + 1;
                        queue.add(new Node(nx, ny, z));
                    }
                } else if (map[nx][ny] == 1 && z + 1 <= K) {
                    if (dp[nx][ny][z + 1] > dp[x][y][z] + 1) {
                        dp[nx][ny][z + 1] = dp[x][y][z] + 1;
                        queue.add(new Node(nx, ny, z + 1));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N][M][K + 1]; // 최소 경로 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] splits = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(splits[j]);
            }
        }

        System.out.println(BFS());
    }
}
