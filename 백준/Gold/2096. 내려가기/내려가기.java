
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x, y, score;

        Node(int x, int y, int score) {
            this.x = x;
            this.y = y;
            this.score = score;
        }
    }

    static int N;
    static int[][] map;
    static int MIN_VAL = Integer.MAX_VALUE;
    static int MAX_VAL = Integer.MIN_VALUE;
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][3][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j][0] = Integer.MAX_VALUE;
                dp[i][j][1] = Integer.MIN_VALUE;
            }
        }

        dp[N - 1][0][0] = map[N - 1][0];
        dp[N - 1][0][1] = map[N - 1][0];

        dp[N - 1][1][0] = map[N - 1][1];
        dp[N - 1][1][1] = map[N - 1][1];

        dp[N - 1][2][0] = map[N - 1][2];
        dp[N - 1][2][1] = map[N - 1][2];

        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int ny = j + dy[k];
                    if (!(0 <= ny && ny < 3)) {
                        continue;
                    }
                    dp[i][j][0] = Math.min(dp[i][j][0], dp[i + 1][ny][0] + map[i][j]);
                    dp[i][j][1] = Math.max(dp[i][j][1], dp[i + 1][ny][1] + map[i][j]);
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            MIN_VAL = Math.min(MIN_VAL, dp[0][i][0]);
            MAX_VAL = Math.max(MAX_VAL, dp[0][i][1]);
        }
        System.out.println(MAX_VAL + " " + MIN_VAL);
    }
}
