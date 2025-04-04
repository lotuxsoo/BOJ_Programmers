
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int x, y, broken, dist;

        Node(int x, int y, int broken, int dist) {
            this.x = x;
            this.y = y;
            this.broken = broken;
            this.dist = dist;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = line[j] - '0';
            }
        }

        int[][][] visited = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(visited[i][j], INF);
            }
        }
        visited[0][0][0] = 1;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, 1));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y, broken = cur.broken, dist = cur.dist;

            if (x == N - 1 && y == M - 1) {
                System.out.println(dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                    continue;
                } else if (map[nx][ny] == 0) {
                    if (visited[nx][ny][broken] > dist + 1) {
                        visited[nx][ny][broken] = dist + 1;
                        queue.add(new Node(nx, ny, broken, dist + 1));
                    }
                } else if (broken == 0 && map[nx][ny] == 1) {
                    if (visited[nx][ny][1] > dist + 1) {
                        visited[nx][ny][1] = dist + 1;
                        queue.add(new Node(nx, ny, 1, dist + 1));
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
