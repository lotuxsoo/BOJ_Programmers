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

        int k = 1;
        while (k < N) {
            k *= 2;
        }

        // 세그먼트 트리 생성 (최대/최소)
        long[] maxTree = new long[2 * k];
        long[] minTree = new long[2 * k];

        for (int i = k; i < k + N; i++) {
            String input = br.readLine();
            maxTree[i] = Long.parseLong(input);
            minTree[i] = Long.parseLong(input);
        }

        for (int i = k - 1; i > 0; i--) {
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]);
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
        }

        // 질의값 찾기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // [a,b]에서 최소/최대 찾기
            int s_idx = a + k - 1;
            int e_idx = b + k - 1;
            Long MIN = Long.MAX_VALUE;
            Long MAX = Long.MIN_VALUE;
            while (s_idx <= e_idx) {
                if (s_idx % 2 == 1) {
                    MIN = Math.min(MIN, minTree[s_idx]);
                    MAX = Math.max(MAX, maxTree[s_idx]);
                    s_idx++;
                }
                if (e_idx % 2 == 0) {
                    MIN = Math.min(MIN, minTree[e_idx]);
                    MAX = Math.max(MAX, maxTree[e_idx]);
                    e_idx--;
                }
                s_idx /= 2;
                e_idx /= 2;
            }
            System.out.println(MIN + " " + MAX);
        }
    }
}
