import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M; // 세로,가로
    static int[][] graph;
    static boolean[][] visited;
    // 동,서,남,북
    static int[] dx = {0, 0, 1, -1}; // 수직 이동
    static int[] dy = {1, -1, 0, 0}; // 수평 이동

    static class Node {
        int x, y, distance;

        Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static boolean canGo(int x, int y) {
        return 0 <= x && x <= N - 1 && 0 <= y && y <= M - 1;
    }

    static int BFS(int x, int y) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        visited[x][y] = true;
        queue.offer(new Node(x, y, 1));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.x == N - 1 && current.y == M - 1) {
                return current.distance;
            }

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];
                if (canGo(newX, newY) && graph[newX][newY] == 1 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new Node(newX, newY, current.distance + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(split[j]);
            }
        }

        System.out.println(BFS(0, 0));
    }
}
