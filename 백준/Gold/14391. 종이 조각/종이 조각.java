import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int getMaskScore(int mask) {
        int rowScore = 0; // 1이면
        int colScore = 0; // 0이면

        // 가로줄 계산
        for (int i = 0; i < N; i++) { // 열
            int rowNum = 0;
            for (int j = 0; j < M; j++) { // 행
                int k = i * M + j;
                if ((mask & (1 << k)) != 0) { // 1이면
                    rowNum = rowNum * 10 + A[i][j];
                } else { // 0이면
                    rowScore += rowNum;
                    rowNum = 0;
                }
            }
            rowScore += rowNum;
        }

        // 세로 탐색
        for (int j = 0; j < M; j++) { // 행
            int colNum = 0;
            for (int i = 0; i < N; i++) { // 열
                int k = i * M + j;
                if ((mask & (1 << k)) == 0) { // 0이면
                    colNum = colNum * 10 + A[i][j];
                } else {
                    colScore += colNum;
                    colNum = 0;
                }
            }
            colScore += colNum;
        }

        return colScore + rowScore;
    }

    static int N, M; // 세로,가로
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                A[i][j] = ch[j] - '0';
            }
        }

        int MAX_VAL = Integer.MIN_VALUE;

        // 비트마스킹 탐색 (모든 조합 탐색)
        for (int mask = 0; mask < (1 << M * N); mask++) {

            MAX_VAL = Math.max(MAX_VAL, getMaskScore(mask));
        }

        System.out.println(MAX_VAL);
    }
}
