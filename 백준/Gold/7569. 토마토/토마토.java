
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int M, N, H;
    static int[][][] map;
    static int[] dm = {-1, 1, 0, 0, 0, 0};
    static int[] dn = {0, 0, -1, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};
    static int riped = 0;
    static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M]; // 높이,세로,가로
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 1) {
                        queue.add(new int[]{i, j, k});
                        riped++;
                    }
                    if (map[i][j][k] != -1) {
                        total++;
                    }
                }
            }
        }

        if (riped == total) {
            System.out.println(0);
            return;
        }

        int days = 0;

        while (!queue.isEmpty()) {
            days++;
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                int h = cur[0], n = cur[1], m = cur[2];

                for (int i = 0; i < 6; i++) {
                    int nh = h + dh[i];
                    int nn = n + dn[i];
                    int nm = m + dm[i];

                    if (!(0 <= nh && nh < H && 0 <= nn && nn < N && 0 <= nm && nm < M)) {
                        continue;
                    }

                    if (map[nh][nn][nm] == 0) {
                        map[nh][nn][nm] = 1;
                        queue.add(new int[]{nh, nn, nm});
                        riped++;
                    }
                }
            }

            if (total == riped) {
                System.out.println(days);
                return;
            }
        }

        if (total != riped) {
            System.out.println(-1);
        }
    }
}
