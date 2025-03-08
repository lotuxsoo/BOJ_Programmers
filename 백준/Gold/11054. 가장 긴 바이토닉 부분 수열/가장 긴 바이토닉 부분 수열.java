
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] A;
    static int[] I;
    static int[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // A[i]를 마지막 원소로 하는 가장 긴 증가/감소 부분수열의 길이
        I = new int[N];
        D = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    I[i] = Math.max(I[i], I[j] + 1);
                }
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j > i; j--) {
                if (A[i] > A[j]) {
                    D[i] = Math.max(D[i], D[j] + 1);
                }
            }
        }

        int max = 1;

        for (int i = 0; i < N; i++) {
            max = Math.max(max, I[i] + D[i] + 1);
        }

        System.out.println(max);
    }
}
