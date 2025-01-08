import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int MAX_VAL = Integer.MIN_VALUE;
    static boolean[][] visited;

    static int getSafetyZone(int[][] copyMap) {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (copyMap[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void goVirus(int[][] copyMap) {
        boolean[][] visited = new boolean[N + 1][M + 1];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!visited[i][j] && copyMap[i][j] == 2) {
                    visited[i][j] = true;
                    queue.add(new int[]{i, j});

                    while (!queue.isEmpty()) {
                        int[] now = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = now[0] + dx[k];
                            int ny = now[1] + dy[k];
                            if ((1 <= nx && nx <= N && 1 <= ny && ny <= M) && !visited[nx][ny] && map[nx][ny] != 1) {
                                copyMap[nx][ny] = 2; // 전파
                                visited[nx][ny] = true;
                                queue.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
        }
    }

    static void DFS(int cnt) {
        if (cnt == 3) {
            int[][] copyMap = new int[map.length][];
            for (int i = 0; i < map.length; i++) {
                copyMap[i] = Arrays.copyOf(map[i], map[i].length);
            }
            goVirus(copyMap);
            MAX_VAL = Math.max(MAX_VAL, getSafetyZone(copyMap));
            return;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    visited[i][j] = true;
                    map[i][j] = 1;
                    DFS(cnt + 1);
                    map[i][j] = 0;
                    visited[i][j] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N + 1][M + 1];
        // 벽 3개 세우기
        DFS(0);

        // 바이러스 전파

        // 안전영역 최댓값 갱신
        System.out.println(MAX_VAL);
    }
}
