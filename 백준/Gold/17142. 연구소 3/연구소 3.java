
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean finish(int[][] cloneMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cloneMap[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static int goVirus(ArrayList<int[]> list) {
        boolean[][] visited = new boolean[N][N];

        int[][] cloneMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            cloneMap[i] = map[i].clone();
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int[] arr : list) {
            queue.add(arr);
            cloneMap[arr[0]][arr[1]] = 3;
            visited[arr[0]][arr[1]] = true;
        }

        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (finish(cloneMap)) {
                return time;
            }
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                    if (!(0 <= nx && nx < N && 0 <= ny && ny < N) || cloneMap[nx][ny] == 1) {
                        continue;
                    }

                    if ((cloneMap[nx][ny] == 0 || cloneMap[nx][ny] == 2) && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        cloneMap[nx][ny] = 3;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }

            time++;
        }

        return INF; // 바이러스 퍼뜨릴 수 없으면
    }

    static void dfs(int cnt, int start, ArrayList<int[]> list) {
        if (cnt == M) { // 바이러스 위치 M개 고름
            MIN_TIME = Math.min(MIN_TIME, goVirus(list));
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(virus.get(i));
                dfs(cnt + 1, i + 1, list);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    static int N, M;
    static int[][] map;
    static ArrayList<int[]> virus = new ArrayList<>();
    static boolean[] visited;
    static int MIN_TIME = 1_000_000_000;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[virus.size()];
        dfs(0, 0, new ArrayList<>());

        System.out.println(MIN_TIME == INF ? -1 : MIN_TIME);
    }
}
