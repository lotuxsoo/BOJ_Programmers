
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String P = br.readLine();

        int n = S.length(), m = P.length();

        if (n < m) {
            System.out.println(0);
            return;
        }

        int sum1 = 0, sum2 = 0;

        for (int i = 0; i < m; i++) {
            sum1 += S.charAt(i);
            sum2 += P.charAt(i);
        }

        for (int i = 0; i <= n - m; i++) {

            if (i != 0 && i - 1 + m < n) {
                sum1 -= S.charAt(i - 1);
                sum1 += S.charAt(i - 1 + m);
            }

            if (sum1 != sum2) {
                continue;
            }

            boolean found = true;
            for (int j = 0; j < m; j++) {
                if (S.charAt(i + j) != P.charAt(j)) {
                    found = false;
                    break;
                }
            }

            if (found) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

}
