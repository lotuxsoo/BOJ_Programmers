
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static void check(int num) {
        int[] cur = map.get(num);
        int x = cur[0], y = cur[1];
        board[x][y] = 0;

        if (!rows[x]) {
            boolean found = true;
            for (int i = 0; i < 5; i++) {
                if (board[x][i] != 0) {
                    found = false;
                    break;
                }
            }
            if (found) {
                rows[x] = true;
                count++;
            }
        }

        if (!cols[y]) {
            boolean found = true;
            for (int i = 0; i < 5; i++) {
                if (board[i][y] != 0) {
                    found = false;
                    break;
                }
            }
            if (found) {
                cols[y] = true;
                count++;
            }
        }

        if ((x == y) && !diagonals[0]) { // 정방향 확인
            boolean found = true;
            for (int i = 0; i < 5; i++) {
                if (board[i][i] != 0) {
                    found = false;
                    break;
                }
            }
            if (found) {
                diagonals[0] = true;
                count++;
            }
        }

        if ((x + y == 4) && !diagonals[1]) {
            boolean found = true;
            for (int i = 0; i < 5; i++) {
                if (board[i][4 - i] != 0) {
                    found = false;
                    break;
                }
            }
            if (found) {
                diagonals[1] = true;
                count++;
            }
        }
    }

    static int[][] board;
    static Map<Integer, int[]> map = new HashMap<>();
    static int count = 0;
    static boolean[] rows = new boolean[5]; // 가로줄
    static boolean[] cols = new boolean[5]; // 세로줄
    static boolean[] diagonals = new boolean[2]; // 정방향,역방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                map.put(board[i][j], new int[]{i, j});
            }
        }

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken());
                check(num);
                if (count >= 3) {
                    System.out.println(i * 5 + j + 1);
                    return;
                }
            }
        }
    }
}
