import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x, y, flag; // 0:안부숨, 1:부숨

        Node(int x, int y, int flag) {
            this.x = x;
            this.y = y;
            this.flag = flag;
        }
    }

    static int BFS(int[][] map) {
        int N = map.length;
        int M = map[0].length;
        int[][][] visited = new int[N][M][2]; // 0:안부숨, 1:부숨
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        visited[0][0][0] = 1;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y, flag = cur.flag;

            if (x == N - 1 && y == M - 1) {
                return visited[x][y][flag];
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    if (visited[nx][ny][flag] > visited[x][y][flag] + 1) {
                        visited[nx][ny][flag] = visited[x][y][flag] + 1;
                        queue.add(new Node(nx, ny, flag));
                    }
                } else if (map[nx][ny] == 1 && flag == 0) {
                    if (visited[nx][ny][1] > visited[x][y][flag] + 1) {
                        visited[nx][ny][1] = visited[x][y][flag] + 1;
                        queue.add(new Node(nx, ny, 1));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] splits = br.readLine().split("");
            for (int j = 0; j < splits.length; j++) {
                map[i][j] = Integer.parseInt(splits[j]);
            }
        }

        int bfs = BFS(map);
        System.out.println(bfs);
    }
}
