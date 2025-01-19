import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int MOD = 1000000009;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] D = new int[1000001];
        D[1] = 1;
        D[2] = 2;
        D[3] = 4;
        for (int i = 4; i < 1000001; i++) {
            D[i] = (D[i] + D[i - 1]) % MOD;
            D[i] = (D[i] + D[i - 2]) % MOD;
            D[i] = (D[i] + D[i - 3]) % MOD;
        }

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(D[n]);
        }
    }
}
