
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ArrayList<int[]> meetings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");
            int s = Integer.parseInt(sp[0]);
            int e = Integer.parseInt(sp[1]);
            int p = Integer.parseInt(sp[2]);
            meetings.add(new int[]{s, e, p});
        }

        // 끝나는 시간 오름차순
        Collections.sort(meetings, (a, b) -> {
            return Integer.compare(a[1], b[1]);
        });

        int[] dp = new int[N];
        dp[0] = meetings.get(0)[2];
        int max = dp[0];

        for (int i = 1; i < N; i++) {
            dp[i] = meetings.get(i)[2];

            for (int j = i - 1; j >= 0; j--) {
                if (meetings.get(j)[1] <= meetings.get(i)[0]) {
                    dp[i] = Math.max(dp[i], dp[j] + meetings.get(i)[2]);
                }
            }

            dp[i] = Math.max(dp[i], dp[i - 1]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
