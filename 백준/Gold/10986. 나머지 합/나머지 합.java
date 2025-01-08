import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[] sum = new long[N + 1];
        int[] dp = new int[M]; // M으로 나눈 나머지 저장

        long answer = 0;
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + arr[i];
            int remainder = (int) (sum[i] % M);
            if (remainder == 0) {
                answer++;
            }
            dp[remainder]++;
        }

        for (int i = 0; i < M; i++) {
            if (dp[i] > 1) {
                answer += (long) dp[i] * (dp[i] - 1) / 2;
            }
        }

        System.out.println(answer);
    }
}
