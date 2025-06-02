
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int bfs() {
        // 물 먼저 이동
        while (!waterQueue.isEmpty()) {
            int[] cur = waterQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                if ((nx >= 0 && nx < rows && ny >= 0 && ny < cols) && map[nx][ny] != 'X' && map[nx][ny] != 'D'
                        && wVisited[nx][ny] == -1) {
                    wVisited[nx][ny] = wVisited[cur[0]][cur[1]] + 1;
                    waterQueue.add(new int[]{nx, ny});
                }
            }
        }

        Queue<int[]> sQueue = new LinkedList<>();
        sQueue.add(S);

        while (!sQueue.isEmpty()) {
            int[] cur = sQueue.poll();

            if (cur[0] == D[0] && cur[1] == D[1]) {
                return sVisited[cur[0]][cur[1]];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                if ((nx >= 0 && nx < rows && ny >= 0 && ny < cols) && map[nx][ny] != 'X' && sVisited[nx][ny] == -1) {
                    if (wVisited[nx][ny] == -1 || wVisited[nx][ny] > sVisited[cur[0]][cur[1]] + 1) {
                        sVisited[nx][ny] = sVisited[cur[0]][cur[1]] + 1;
                        sQueue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return -1;
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int rows, cols;
    static char[][] map;
    static int[] D = new int[2]; // 비버
    static int[] S = new int[2]; // 고슴도치
    static Queue<int[]> waterQueue = new LinkedList<>();
    static int[][] sVisited;
    static int[][] wVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        rows = Integer.parseInt(sp[0]);
        cols = Integer.parseInt(sp[1]);
        map = new char[rows][cols];

        sVisited = new int[rows][cols];
        wVisited = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(sVisited[i], -1);
            Arrays.fill(wVisited[i], -1);
        }

        for (int i = 0; i < rows; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < cols; j++) {
                map[i][j] = ch[j];
                if (map[i][j] == 'D') {
                    D[0] = i;
                    D[1] = j;
                } else if (map[i][j] == 'S') {
                    S[0] = i;
                    S[1] = j;
                    sVisited[i][j] = 0;
                } else if (map[i][j] == '*') {
                    waterQueue.add(new int[]{i, j});
                    wVisited[i][j] = 0;
                }
            }
        }

        int answer = bfs();
        System.out.println(answer == -1 ? "KAKTUS" : answer);
    }
}
