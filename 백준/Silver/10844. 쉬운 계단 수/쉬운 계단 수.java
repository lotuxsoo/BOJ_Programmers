import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] D = new long[N + 1][10]; // 1~N, 0~9

        for (int i = 1; i <= 9; i++) {
            D[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (0 < j) {
                    D[i][j] = (D[i][j] + D[i - 1][j - 1]) % 1000000000;
                }
                if (j < 9) {
                    D[i][j] = (D[i][j] + D[i - 1][j + 1]) % 1000000000;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum = (sum + D[N][i]) % 1000000000;
        }
        System.out.println(sum);
    }
}