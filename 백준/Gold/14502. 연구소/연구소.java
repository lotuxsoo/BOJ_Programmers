
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static void backtrack(int cnt, int start) { // 조합 뽑기
        if (cnt == 3) {
            goVirus();
            return;
        }

        for (int i = start; i < emptyList.size(); i++) {
            if (!visited[i]) {
                int[] cur = emptyList.get(i);
                map[cur[0]][cur[1]] = 1;
                visited[i] = true;
                backtrack(cnt + 1, i + 1);
                map[cur[0]][cur[1]] = 0;
                visited[i] = false;
            }
        }
    }

    static void goVirus() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        for (int[] cur : virusList) {
            queue.add(cur);
            visited[cur[0]][cur[1]] = true;
        }

        int[][] copied = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            copied[i] = map[i].clone();
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                    continue;
                }

                if (!visited[nx][ny] && copied[nx][ny] == 0) {
                    copied[nx][ny] = 2;
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copied[i][j] == 0) {
                    count++;
                }
            }
        }

        maxSafeZone = Math.max(maxSafeZone, count);
    }

    static int N, M;
    static int[][] map;
    static int maxSafeZone = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<int[]> emptyList = new ArrayList<>();
    static ArrayList<int[]> virusList = new ArrayList<>();
    static boolean[] visited;

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
                if (map[i][j] == 0) {
                    emptyList.add(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    virusList.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[emptyList.size()];
        backtrack(0, 0);

        System.out.println(maxSafeZone);
    }
}
