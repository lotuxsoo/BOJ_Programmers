
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int count(int i, int j) {
        // (i,j)부터 8*8구간 확인
        int cnt1 = 0, cnt2 = 0;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                char actual = board[i + row].charAt(j + col);

                char startB = ((row + col) % 2 == 0) ? 'B' : 'W';
                char startW = ((row + col) % 2 == 0) ? 'W' : 'B';

                if (actual != startB) {
                    cnt1++;
                }
                if (actual != startW) {
                    cnt2++;
                }
            }
        }

        return Math.min(cnt1, cnt2);
    }

    static int N, M;
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

        int result = 8 * 8;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                result = Math.min(result, count(i, j));
            }
        }

        System.out.println(result);
    }
}
