import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int MOD = 1000000000;
        long[][] D = new long[N + 1][10]; // 길이 N에서 끝이 0~9인 계단 수

        // 초기값 설정
        for (int i = 1; i <= 9; i++) {
            D[1][i] = 1;
        }

        // 점화식 계산
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j > 0) {
                    D[i][j] += D[i - 1][j - 1];
                }
                if (j < 9) {
                    D[i][j] += D[i - 1][j + 1];
                }
                D[i][j] %= MOD; // 나머지 연산
            }
        }

        // 결과 계산
        long sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum = (sum + D[N][i]) % MOD;
        }

        System.out.println(sum);
    }
}