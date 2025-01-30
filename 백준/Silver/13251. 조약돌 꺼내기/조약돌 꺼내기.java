import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[M];
        for (int i = 0; i < M; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int sum = Arrays.stream(A).sum();
        int K = Integer.parseInt(br.readLine());

        double answer = 0.0;
        for (int j = 0; j < M; j++) {
            double prob = 1.0;
            for (int i = 0; i < K; i++) {
                prob *= (double) (A[j] - i) / (sum - i);
            }
            answer += prob;
        }
        System.out.println(answer);
    }
}
