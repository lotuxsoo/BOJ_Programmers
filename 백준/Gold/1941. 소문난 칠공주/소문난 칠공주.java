
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static boolean check(int[] selected) {
        boolean[] check = new boolean[7];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        check[0] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            int x = selected[cur] / 5, y = selected[cur] % 5;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (!(0 <= nx && nx < 5 && 0 <= ny && ny < 5)) {
                    continue;
                }

                int idx = nx * 5 + ny;

                // selected에 있는지 확인
                for (int j = 0; j < 7; j++) {
                    if (!check[j] && selected[j] == idx) {
                        check[j] = true;
                        cnt++;
                        queue.add(j);
                        break;
                    }
                }
            }
        }

        return cnt == 7;
    }

    static void backtrack(int depth, int start, int sCnt, int[] selected) {
        if (depth == 7) { // 25개 중에 7개 뽑고나서 연결성 확인
            if (check(selected) && sCnt >= 4) {
                result++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[depth] = i;
                if (map[i / 5][i % 5] == 'S') {
                    backtrack(depth + 1, i + 1, sCnt + 1, selected);
                } else {
                    backtrack(depth + 1, i + 1, sCnt, selected);
                }
                visited[i] = false;
            }
        }
    }

    static int result = 0;
    static char[][] map = new char[5][5];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[] visited = new boolean[25];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                map[i][j] = ch[j];
            }
        }

        backtrack(0, 0,0, new int[7]);

        System.out.println(result);
    }
}
