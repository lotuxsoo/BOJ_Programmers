
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1});

        boolean[][] visited = new boolean[rows][cols];
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == rows - 1 && cur[1] == cols - 1) {
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                if ((nx >= 0 && nx < rows && ny >= 0 && ny < cols) && map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }

        return 0;
    }

    static int rows, cols;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        rows = Integer.parseInt(sp[0]);
        cols = Integer.parseInt(sp[1]);
        map = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < cols; j++) {
                map[i][j] = ch[j] - '0'; // 문자숫자 -> 정수숫자
            }
        }

        System.out.println(bfs());
    }
}
