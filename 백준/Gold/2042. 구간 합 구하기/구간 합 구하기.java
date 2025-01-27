import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 트리 크기 계산
        int k = 1;
        while (k < N) {
            k *= 2;
        }
        long[] S = new long[2 * k];

        // 리프 노드 데이터 입력
        for (int i = k; i < k + N; i++) {
            String input = br.readLine();
            S[i] = Long.parseLong(input);
        }

        // 내부 노드 채우기
        for (int i = k - 1; i > 0; i--) {
            S[i] = S[i * 2] + S[i * 2 + 1];
        }

        // 명령 처리
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) { // 값 변경
                int idx = b + k - 1;
                S[idx] = c;
                while (idx > 1) {
                    idx /= 2;
                    S[idx] = S[idx * 2] + S[idx * 2 + 1];
                }
            } else if (a == 2) { // 구간합 계산
                long sum = 0;
                int s_idx = b + k - 1;
                int e_idx = (int) c + k - 1;

                while (s_idx <= e_idx) {
                    if (s_idx % 2 == 1) {
                        sum += S[s_idx];
                        s_idx++;
                    }
                    if (e_idx % 2 == 0) {
                        sum += S[e_idx];
                        e_idx--;
                    }
                    s_idx /= 2;
                    e_idx /= 2;
                }
                System.out.println(sum);
            }
        }
    }
}