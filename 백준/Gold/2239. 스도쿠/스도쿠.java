
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    static void backtrack(int depth) {
        if (solved) {
            return;
        }

        if (depth == list.size()) {
            print();
            solved = true;  // 첫번째 해만 출력하면됨
            return;
        }

        int x = list.get(depth)[0];
        int y = list.get(depth)[1];

        boolean[] visited = new boolean[10]; // 1~9

        // 행에서 후보 선정
        for (int i = 0; i < 9; i++) {
            if (map[x][i] > 0) {
                visited[map[x][i]] = true;
            }
        }

        // 열에서 후보 선정
        for (int i = 0; i < 9; i++) {
            if (map[i][y] > 0) {
                visited[map[i][y]] = true;
            }
        }

        // 사각형에서 후보 선정
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int nx = startX + i, ny = startY + j;
                if (!(0 <= nx && nx < 9 && 0 <= ny && ny < 9)) {
                    continue;
                }
                if (map[nx][ny] > 0) {
                    visited[map[nx][ny]] = true;
                }
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (!visited[i]) {
                map[x][y] = i;
                backtrack(depth + 1);
                map[x][y] = 0;
            }
        }
    }

    static boolean solved = false;
    static int[][] map = new int[9][9];
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String[] sp = br.readLine().split("");
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(sp[j]);
                if (map[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }

        backtrack(0);

    }
}
