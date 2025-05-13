
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
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
            found = true;
            print();
            return;
        }

        int[] zero = zeros.get(index);
        int x = zero[0];
        int y = zero[1];

        boolean[] numbers = new boolean[10]; // 1~9

        // 가로줄에서 후보 제거
        for (int i = 0; i < 9; i++) {
            if (map[x][i] > 0) {
                numbers[map[x][i]] = true;
            }
        }

        // 세로줄에서 후보 제거
        for (int i = 0; i < 9; i++) {
            if (map[i][y] > 0) {
                numbers[map[i][y]] = true;
            }
        }

        // 3x3 정사각형에서 후보 제거
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (map[i][j] > 0) {
                    numbers[map[i][j]] = true;
                }
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (!numbers[i]) {
                map[x][y] = i;
                backtrack(index + 1);
                map[x][y] = 0;
            }
        }
    }

    static boolean found = false;
    static int[][] map = new int[9][9];
    static ArrayList<int[]> zeros = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String[] sp = br.readLine().split("");
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
