
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void dfs(int x, int y, int mid, int[][] cloneMap) {
        cloneMap[x][y] = -1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if ((0 <= nx && nx < N && 0 <= ny && ny < N) && cloneMap[nx][ny] > mid) {
                dfs(nx, ny, mid, cloneMap);
            }
        }
    }

    static int findSafetyZone(int mid) {
        int count = 0;

        int[][] cloneMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            cloneMap[i] = map[i].clone();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cloneMap[i][j] > mid) {
                    dfs(i, j, mid, cloneMap);
                    count++;
                }
            }
        }

        return count;
    }

    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int result = 0;
        // 단조 증가/감소 성질이 없음, 브루트포스 필요
        for (int i = 0; i < maxHeight; i++) {
            int count = findSafetyZone(i);
            result = Math.max(result, count);
        }

        System.out.println(result);
    }
}
