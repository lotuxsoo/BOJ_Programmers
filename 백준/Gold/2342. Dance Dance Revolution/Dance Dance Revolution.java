import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int cost(int from, int to) {
        if (from == to) {
            return 1;
        }
        if (from == 0) {
            return 2;
        }
        if (Math.abs(from - to) == 2) {
            return 4;
        }
        return 3;
    }

    static int solve(int index, int left, int right) {
        if (index == N) {
            return 0;
        }

        if (dp[index][left][right] != -1) {
            return dp[index][left][right];
        }

        int next = moves.get(index);

        int temp = Integer.MAX_VALUE;

        if (next != left) { // right로 이동
            temp = Math.min(temp,
                    solve(index + 1, left, next) + cost(right, next));
        }

        if (next != right) { // left로 이동
            temp = Math.min(temp,
                    solve(index + 1, next, right) + cost(left, next));
        }

        return dp[index][left][right] = temp;
    }

    static List<Integer> moves = new ArrayList<>();
    static int N;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int t = Integer.parseInt(st.nextToken());
            if (t == 0) {
                break;
            }
            moves.add(t);
        }
        N = moves.size();

        // dp[index][left][right]: index 지시를 고려할때 위치 (l,r)일때의 최소힘
        dp = new int[N + 1][5][5];

        // 초기화 (방문 체크)
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(solve(0, 0, 0));

    }
}
