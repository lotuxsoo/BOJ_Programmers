import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] D = new int[N + 1];
        D[1] = 1;
        if (N == 1) {
            System.out.println(1);
            return;
        }
        D[2] = 2;
        for (int i = 3; i <= N; i++) {
            D[i] = (D[i - 1] + D[i - 2]) % 10007;
        }
        System.out.println(D[N]);
    }
}
