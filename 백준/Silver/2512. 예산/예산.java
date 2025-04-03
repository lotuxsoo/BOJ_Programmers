
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] budget;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        budget = new int[N];
        int total = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
            total += budget[i];
        }
        M = Integer.parseInt(br.readLine());

        Arrays.sort(budget);
        if (total <= M) {
            System.out.println(budget[N - 1]);
        } else {
            // 이분 탐색
            int low = 0, high = budget[N - 1];
            while (low <= high) {
                int mid = (low + high) / 2;
                long sum = 0;

                for (int i = 0; i < N; i++) {
                    sum += Math.min(budget[i], mid);
                }

                if (sum <= M) {
                    result = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            System.out.println(result);
        }
    }
}
