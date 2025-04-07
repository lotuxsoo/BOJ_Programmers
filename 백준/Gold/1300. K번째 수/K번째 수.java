import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static long calculate(long mid) {
        long count = 0;
        for (int i = 1; i <= N; i++) {
            count += Math.min(N, mid / i);
        }
        return count;
    }

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        long low = 1, high = K, answer = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            long count = calculate(mid);
            if (count >= K) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
