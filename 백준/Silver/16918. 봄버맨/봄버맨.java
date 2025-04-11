import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void bomb(int i, int j) {

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k], ny = j + dy[k];
            if (0 <= nx && nx < R && 0 <= ny && ny < C) {
                if (!visited[nx][ny] && map[nx][ny] == 3) {
                    continue;
                }
                map[nx][ny] = 0;
                visited[nx][ny] = true;
            }
        }
    }

    static int R, C, N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = ch[j] == '.' ? 0 : 2;
            }
        }

        for (int k = 1; k < N; k++) {
            visited = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (visited[i][j]) {
                        continue; // 폭발 처리 넘어감
                    }

                    if (++map[i][j] == 4) {
                        map[i][j] = 0;
                        bomb(i, j);
                    }
                    visited[i][j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j] == 0 ? '.' : 'O');
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
