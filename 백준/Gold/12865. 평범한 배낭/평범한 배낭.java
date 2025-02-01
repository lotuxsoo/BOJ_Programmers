import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] info = new int[N + 1][2];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken()); // 무게
            info[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }

        // dp[i][j]: i번째 물건까지 고려했을때 j무게 이하로 넣을수있는 가치 최댓값
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < K + 1; j++) {
                if (info[i][0] <= j) { // 현재 물건 넣을수있으면
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - info[i][0]] + info[i][1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
