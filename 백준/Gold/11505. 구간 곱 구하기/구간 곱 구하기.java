import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간곱 횟수

        // 세그먼트 트리 생성 (구간곱)
        int k = 1;
        while (k < N) {
            k *= 2;
        }

        long[] T = new long[2 * k];
        for (int i = k; i < k + N; i++) {
            T[i] = Long.parseLong(br.readLine());
        }

        for (int i = k - 1; i > 0; i--) {
            T[i] = (T[i * 2] * T[i * 2 + 1]) % MOD;
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                int s_idx = b + k - 1;
                T[s_idx] = c;
                while (s_idx > 0) {
                    s_idx /= 2;
                    T[s_idx] = (T[s_idx * 2] * T[s_idx * 2 + 1]) % MOD;
                }
            } else if (a == 2) {
                long sum = 1;
                int s_idx = b + k - 1;
                int e_idx = c + k - 1;
                while (s_idx <= e_idx) {
                    if (s_idx % 2 == 1) {
                        sum = (sum * T[s_idx]) % MOD;
                        s_idx++;
                    }
                    if (e_idx % 2 == 0) {
                        sum = (sum * T[e_idx]) % MOD;
                        e_idx--;
                    }
                    s_idx /= 2;
                    e_idx /= 2;
                }
                System.out.println(sum % MOD);
            }
        }
    }
}
