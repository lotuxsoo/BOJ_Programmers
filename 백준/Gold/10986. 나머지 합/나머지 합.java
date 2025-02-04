import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 합배열 저장
        long[] S = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            S[i] = S[i - 1] + A[i];
        }

        // 같은 나머지 개수 저장
        long[] C = new long[M];
        long answer = 0;

        // 합배열을 M으로 나눈 나머지값
        for (int i = 1; i <= N; i++) {
            int remainder = (int) (S[i] % M);
            C[remainder]++;
        }

        answer += C[0];
        for (int i = 0; i < M; i++) {
            if (C[i] >= 2) {
                // 나머지가 같은 인덱스중 2개를 뽑
                answer += C[i] * (C[i] - 1) / 2;
            }
        }

        System.out.println(answer);
    }
}
