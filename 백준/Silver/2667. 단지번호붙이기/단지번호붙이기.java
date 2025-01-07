import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Integer> answerList = new ArrayList<>();

    static boolean cango(int x, int y) {
        if (!(0 <= x && x < N && 0 <= y && y < N)) {
            return false;
        }
        if (visited[x][y]) {
            return false;
        }
        if (map[x][y] == 0) {
            return false;
        }
        return true;
    }

    static int DFS(int x, int y) {
        int cnt = 1;
        visited[x][y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (cango(nx, ny)) {
                cnt += DFS(nx, ny);
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] splits = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(splits[j]);
            }
        }
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    int cnt = DFS(i, j);
                    answerList.add(cnt);
                }
            }
        }

        System.out.println(answerList.size());
        Collections.sort(answerList);
        for (int answer : answerList) {
            System.out.println(answer);
        }
    }
}
