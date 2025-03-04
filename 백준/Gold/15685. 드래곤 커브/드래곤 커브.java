
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static void dfs(int gen, ArrayList<Integer> directions) {
        if (gen == 0) {
            return;
        }

        int size = directions.size();
        for (int i = size - 1; i >= 0; i--) {
            int newd = (directions.get(i) + 1) % 4;
            directions.add(newd);
        }

        dfs(gen - 1, directions);
    }

    static void solve(int x, int y, int d, int g) {
        ArrayList<Integer> directions = new ArrayList<>();
        directions.add(d);
        dfs(g, directions);

        checked[x][y] = true;
        for (int dir : directions) {
            int nx = x + dx[dir], ny = y + dy[dir];
            if ((0 <= nx && nx <= 100 && 0 <= ny && ny <= 100) && !checked[nx][ny]) {
                checked[nx][ny] = true;
            }
            x = nx;
            y = ny;
        }
    }

    static int N;
    static boolean[][] checked;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        checked = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            solve(x, y, d, g);
        }

        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (checked[i][j] && checked[i + 1][j] && checked[i][j + 1] && checked[i + 1][j + 1]) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
