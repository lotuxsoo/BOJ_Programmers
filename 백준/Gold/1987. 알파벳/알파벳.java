
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static void dfs(int x, int y, int cnt, HashSet<Character> set) {
        MAX = Math.max(MAX, cnt);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if ((0 <= nx && nx < R && 0 <= ny && ny < C) && !set.contains(map[nx][ny])) {
                set.add(map[nx][ny]);
                dfs(nx, ny, cnt + 1, set);
                set.remove(map[nx][ny]);
            }
        }
    }

    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        HashSet<Character> set = new HashSet<>();
        set.add(map[0][0]);
        dfs(0, 0, 1, set);
        System.out.println(MAX);
    }
}
