
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static void paint(int x, int y) {
        for (int i = x; i < x + 10; i++) {
            for (int j = y; j < y + 10; j++) {
                if (i >= 0 && i < 100 && j >= 0 && j < 100) {
                    paper[i][j]++;
                }
            }
        }
    }

    static int N;
    static int[][] paper = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");
            int x = 100 - Integer.parseInt(sp[1]) - 10; // 세로
            int y = Integer.parseInt(sp[0]) - 1; // 가로
            paint(x, y);
        }

        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (paper[i][j] > 0) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
