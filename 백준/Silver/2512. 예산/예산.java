
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] budget;
    static int MAX_VAL = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        budget = new int[N];
        int total = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
            total += budget[i];
            MAX_VAL = Math.max(MAX_VAL, budget[i]);
        }
        M = Integer.parseInt(br.readLine());

        if (total <= M) {
            System.out.println(MAX_VAL);
        } else {
            Arrays.sort(budget);
            int upper = MAX_VAL;

            while (true) {
                int max = Integer.MIN_VALUE;
                upper--;
                int sum = 0;
                for (int i = 0; i < N; i++) {
                    int s = budget[i] > upper ? upper : budget[i];
                    sum += s;
                    max = Math.max(max, s);
                }
                if (sum <= M) {
                    System.out.println(max);
                    break;
                }
            }
        }

    }
}
