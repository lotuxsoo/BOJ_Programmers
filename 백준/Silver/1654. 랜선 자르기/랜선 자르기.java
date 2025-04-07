
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int K, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        K = Integer.parseInt(sp[0]);
        N = Integer.parseInt(sp[1]);

        int[] lines = new int[K];
        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }

        // 이분탐색
        Arrays.sort(lines);

        long high = lines[K - 1], low = 1, maxLen = 0;

        while (low <= high) {
            long len = low + (high - low) / 2;
            long cnt = 0;
            for (int line : lines) {
                cnt += line / len;
            }
            if (cnt >= N) {
                maxLen = Math.max(maxLen, len);
                low = len + 1;
            } else {
                high = len - 1;
            }
        }

        System.out.println(maxLen);
    }
}
