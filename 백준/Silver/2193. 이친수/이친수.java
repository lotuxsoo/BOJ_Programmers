import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] D = new long[N + 1][2];

        D[1][1] = 1;

        if (N == 1) {
            System.out.println(1);
            return;
        }

        D[2][0] = 1;
        for (int i = 2; i <= N; i++) {
            D[i][0] = D[i - 1][0] + D[i - 1][1];
            D[i][1] = D[i - 1][0];
        }

        System.out.println(D[N][0] + D[N][1]);
    }
}
