import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물품의 수
        int K = Integer.parseInt(st.nextToken()); // 버틸수있는 무게

        int[] weights = new int[N + 1];
        int[] values = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken()); // 무게
            values[i] = Integer.parseInt(st.nextToken()); // 가치
        }

        // dp[i][j] : j만큼 무게까지 담을때, i개를 담은 최대 가치값
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i < N + 1; i++) { // 물건 개수만큼 반복
            for (int w = 0; w < K + 1; w++) { // 배낭 무게별 계산
                if (weights[i] > w) { // 현재 물건을 넣을 수 없는 경우
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i]] + values[i]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
