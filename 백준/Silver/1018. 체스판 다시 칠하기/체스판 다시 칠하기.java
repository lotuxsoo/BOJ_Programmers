
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int count(int i, int j) {
        // (i,j)부터 8*8구간 확인
        int cnt1 = 0, cnt2 = 0;
        for (int k = i; k < i + 8; k++) {
            String substr = board[k].substring(j, j + 8);
            if (k % 2 == i % 2) {
                for (int t = 0; t < 8; t++) {
                    if (substr.charAt(t) != patterns[0].charAt(t)) {
                        cnt1++;
                    }
                    if (substr.charAt(t) != patterns[1].charAt(t)) {
                        cnt2++;
                    }
                }
            } else {
                for (int t = 0; t < 8; t++) {
                    if (substr.charAt(t) != patterns[1].charAt(t)) {
                        cnt1++;
                    }
                    if (substr.charAt(t) != patterns[0].charAt(t)) {
                        cnt2++;
                    }
                }
            }
        }
        return Math.min(cnt1, cnt2);
    }

    static int N, M;
    static String[] patterns = {"WBWBWBWB", "BWBWBWBW"};
    static String[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);
        board = new String[N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine();
        }

        int result = 8 * 8; // 다시 칠해야 하는 정사각형 최소 갯수

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                result = Math.min(result, count(i, j));
            }
        }

        System.out.println(result);
    }
}
