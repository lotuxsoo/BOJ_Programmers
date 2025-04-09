
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x, y, cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static Node[][] map;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 1;
        StringBuilder sb = new StringBuilder();

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            map = new Node[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int cost = Integer.parseInt(st.nextToken());
                    map[i][j] = new Node(i, j, cost);
                }
            }

            dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], INF);
            }
            dp[0][0] = map[0][0].cost;

            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
            pq.add(new Node(0, 0, dp[0][0]));
            int minCost = INF;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (cur.x == N - 1 && cur.y == N - 1) {
                    minCost = Math.min(minCost, dp[cur.x][cur.y]);
                    break;
                }

                if (dp[cur.x][cur.y] < cur.cost) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i], ny = cur.y + dy[i];
                    if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
                        continue;
                    }

                    if (dp[nx][ny] > dp[cur.x][cur.y] + map[nx][ny].cost) {
                        dp[nx][ny] = dp[cur.x][cur.y] + map[nx][ny].cost;
                        pq.add(new Node(nx, ny, dp[nx][ny]));
                    }
                }
            }

            sb.append("Problem ").append(T++).append(": ").append(minCost).append("\n");
        }
        System.out.println(sb.toString());
    }
}
