
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int dfs(int x, int y) {
        int cnt = 1;
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if ((nx >= 0 && nx < N && ny >= 0 && ny < N) && map[nx][ny] == 1 && !visited[nx][ny]) {
                cnt += dfs(nx, ny);
            }
        }
        return cnt;
    }

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = ch[j] - '0';
            }
        }

        visited = new boolean[N][N];
        int count = 0;
        ArrayList<Integer> countList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    count++;
                    countList.add(dfs(i, j));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");
        Collections.sort(countList);
        for (int x : countList) {
            sb.append(x).append("\n");
        }
        System.out.println(sb.toString());
    }
}
