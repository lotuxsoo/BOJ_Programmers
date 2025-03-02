
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        int[][] visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }
        visited[x][y] = 0;

        int maxDist = 0, maxValue = map[x][y];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0], cy = cur[1];

            // 최대 거리 갱신 + 값 갱신
            if (maxDist < visited[cx][cy]) {
                maxDist = visited[cx][cy];
                maxValue = map[cx][cy]; // 새로운 거리에서 방 값 갱신
            } else if (maxDist == visited[cx][cy]) {
                maxValue = Math.max(maxValue, map[cx][cy]); // 같은 거리에서 최댓값 선택
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i], ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] != -1 || map[nx][ny] == 0) {
                    continue;
                }

                visited[nx][ny] = visited[cx][cy] + 1;
                queue.add(new int[]{nx, ny});
            }
        }

        return new int[]{maxDist, maxValue + map[x][y]}; // 시작 방 + 최장 거리 방의 값
    }

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int MAX_VAL = 0;
        int MAX_DIST = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;

                int[] info = bfs(i, j);
                if (info[0] > MAX_DIST) {
                    MAX_DIST = info[0];
                    MAX_VAL = info[1];
                } else if (info[0] == MAX_DIST) {
                    MAX_VAL = Math.max(MAX_VAL, info[1]);
                }
            }
        }

        System.out.println(MAX_VAL);
    }
}