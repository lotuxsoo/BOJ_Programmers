import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int getScore(int mask) {
        int score = 0;

        for (int i = 0; i < N - 1; i++) { // 0~N-1
            if ((mask & (1 << i)) != 0) { // i 포함
                for (int j = i + 1; j < N; j++) { // i+1~N
                    if ((mask & (1 << j)) != 0) { // j 포함
                        score += S[i][j] + S[j][i];
                    }
                }
            }
        }

        return score;
    }

    static int N;
    static int[][] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int MIN_VAL = Integer.MAX_VALUE;

        // 비트마스킹으로 팀 구성
        for (int mask = 1; mask < (1 << N) - 1; mask++) { // 0001~1110 까지 탐색
            int startScore = getScore(mask);
            int linkMask = mask ^ ((1 << N) - 1);
            int linkScore = getScore(linkMask);
            MIN_VAL = Math.min(MIN_VAL, Math.abs(startScore - linkScore));
        }

        System.out.println(MIN_VAL);
    }
}
