import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int[] A = new int[M + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = 0;
        for (int i = 1; i <= M; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            total += A[i];
        }
        int K = Integer.parseInt(br.readLine());

        double answer = 0.0;
        for (int i = 1; i <= M; i++) {
            double prob = 1.0;
            for (int k = 0; k < K; k++) {
                prob *= (double) (A[i] - k) / (total - k);
            }
            answer += prob;
        }

        System.out.println(answer);
    }
}
