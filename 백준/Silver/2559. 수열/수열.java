
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        temp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            temp[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += temp[i];
        }

        int result = sum;

        for (int i = K; i < N; i++) {
            sum -= temp[i - K];
            sum += temp[i];
            result = Math.max(result, sum);
        }

        System.out.println(result);
    }
}
