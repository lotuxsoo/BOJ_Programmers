
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void backtrack(int index) {
        if (found) {
            return;
        }

        if (index == zeros.size()) {
            print();
            found = true;
            return;
        }

        int[] zero = zeros.get(index);
        int x = zero[0];
        int y = zero[1];

        boolean[] number = new boolean[10]; // 1~9

        // 속한 가로줄 검토
        for (int i = 0; i < 9; i++) {
            if (map[x][i] > 0) {
                number[map[x][i]] = true;
            }
        }

        // 속한 세로줄 검토
        for (int i = 0; i < 9; i++) {
            if (map[i][y] > 0) {
                number[map[i][y]] = true;
            }
        }

        // 속한 3x3 정사각형 검토
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (map[i][j] > 0) {
                    number[map[i][j]] = true;
                }
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (!number[i]) {
                map[x][y] = i;
                backtrack(index + 1);
                map[x][y] = 0; // 백트래킹
            }
        }
    }

    static boolean found = false;
    static int[][] map = new int[9][9];
    static ArrayList<int[]> zeros = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String[] sp = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(sp[j]);
                if (map[i][j] == 0) {
                    zeros.add(new int[]{i, j});
                }
            }
        }

        backtrack(0);
    }
}
