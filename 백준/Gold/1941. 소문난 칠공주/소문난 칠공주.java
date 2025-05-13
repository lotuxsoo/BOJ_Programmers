
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static boolean bfs(int[] selected) {
        int count = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(selected[0]);

        boolean[] checked = new boolean[25];
        checked[selected[0]] = true;
        count++;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / 5, y = cur % 5;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (!(0 <= nx && nx < 5 && 0 <= ny && ny < 5)) {
                    continue;
                }

                int idx = nx * 5 + ny;
                for (int s : selected) {
                    if (s == idx && !checked[idx]) {
                        checked[idx] = true;
                        count++;
                        queue.add(idx);
                    }
                }
            }
        }

        return count == 7;
    }

    static void backtrack(int depth, int sCnt, int yCnt, int start, int[] selected) {
        if (yCnt >= 4) {
            return;
        }

        if (depth == 7) {
            if (sCnt >= 4) {
                if (bfs(selected)) {
                    result++;
                }
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[depth] = i;
                if (arr[i] == 'S') {
                    backtrack(depth + 1, sCnt + 1, yCnt, i + 1, selected);
                } else {
                    backtrack(depth + 1, sCnt, yCnt + 1, i + 1, selected);
                }
                visited[i] = false;
            }
        }
    }

    static char[][] map = new char[5][5];
    static char[] arr = new char[25];
    static boolean[] visited = new boolean[25];
    static int result = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                map[i][j] = ch[j];
                arr[i * 5 + j] = ch[j];
            }
        }

        backtrack(0, 0, 0, 0, new int[7]);

        System.out.println(result);
    }
}
