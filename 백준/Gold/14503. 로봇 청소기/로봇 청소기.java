
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int solve(int r, int c, int d) {
        int count = 0;
        int x = r, y = c;

        while (true) {
            // 현재칸 청소
            if (map[x][y] == 0 && !visited[x][y]) {
                count++;
                visited[x][y] = true;
            }

            boolean found = false;

            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;
                int nx = x + dx[d], ny = y + dy[d];

                if ((0 <= nx && nx < N && 0 <= ny && ny < M) && !visited[nx][ny] && map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    found = true;
                    break;
                }
            }

            if (!found) {
                int nx = x - dx[d], ny = y - dy[d];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < M) || map[nx][ny] == 1) {
                    return count;
                }
                x = nx;
                y = ny;
            }
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    // 북,동,남,서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        System.out.println(solve(r, c, d));
    }
}
