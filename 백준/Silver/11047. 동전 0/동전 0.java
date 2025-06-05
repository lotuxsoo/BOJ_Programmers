
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, K;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        K = Integer.parseInt(sp[1]);
        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (K >= coins[i]) {
                count += K / coins[i];
                K %= coins[i];
            }
        }
        System.out.println(count);
    }
}
