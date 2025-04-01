
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void backtrack(int idx, int val) {
        if (idx == N) {
            MAX_VAL = Math.max(MAX_VAL, val);
            MIN_VAL = Math.min(MIN_VAL, val);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (oper[i] > 0) {
                oper[i]--;
                if (i == 0) {
                    backtrack(idx + 1, val + A[idx]);
                } else if (i == 1) {
                    backtrack(idx + 1, val - A[idx]);
                } else if (i == 2) {
                    backtrack(idx + 1, val * A[idx]);
                } else if (i == 3) {
                    backtrack(idx + 1, val / A[idx]);
                }
                oper[i]++;
            }
        }
    }

    static int N;
    static int[] A;
    static int[] oper = new int[4];
    static int MAX_VAL = -1_000_000_000;
    static int MIN_VAL = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }

        backtrack(1, A[0]);

        System.out.println(MAX_VAL);
        System.out.println(MIN_VAL);
    }
}
