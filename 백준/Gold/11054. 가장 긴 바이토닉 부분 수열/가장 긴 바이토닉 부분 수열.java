
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] A;
    static int[] increase;
    static int[] decrease;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // dp[v]: A[v]를 마지막 원소로 하는 최장 증가/감소 길이
        increase = new int[N];
        decrease = new int[N];

        for (int i = 0; i < N; i++) {
            increase[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    increase[i] = Math.max(increase[i], increase[j] + 1);
                }
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            decrease[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (A[i] > A[j]) {
                    decrease[i] = Math.max(decrease[i], decrease[j] + 1);
                }
            }
        }

        int MAX = 1;

        for (int i = 0; i < N; i++) {
            MAX = Math.max(MAX, increase[i] + decrease[i] - 1);
        }

        System.out.println(MAX);
    }
}
