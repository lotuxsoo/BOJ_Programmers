import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] D = new long[N + 1][2];
        // D[i][0]: i 길이에서 끝이 0인 개수
        // D[i][1]: i 길이에서 끝이 1인 개수

        D[1][0] = 0;
        D[1][1] = 1;
        for (int i = 2; i <= N; i++) {
            D[i][1] = D[i - 1][0];
            D[i][0] = D[i - 1][0] + D[i - 1][1];
        }

        long answer = D[N][1] + D[N][0];
        System.out.println(answer);
    }
}
