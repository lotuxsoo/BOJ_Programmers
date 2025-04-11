
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, sum = 0, result = 0;

        while (true) {
            if (sum >= M) {
                if (sum == M) {
                    result++;
                }
                sum -= A[start++];
            } else if (end == N) { // 배열 범위: 0~N-1
                break;
            } else if (sum < M) {
                sum += A[end++];
            }
        }

        System.out.println(result);
    }
}
