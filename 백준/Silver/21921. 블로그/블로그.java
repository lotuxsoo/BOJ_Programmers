import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, X;
    static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        X = Integer.parseInt(sp[1]);
        visit = new int[N];
        sp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            visit[i] = Integer.parseInt(sp[i]);
        }

        long sum = 0;
        for (int i = 0; i < X; i++) {
            sum += visit[i];
        }
        long maxSum = sum;
        int count = maxSum == 0 ? 0 : 1;

        for (int i = X; i < N; i++) {
            sum += visit[i];
            sum -= visit[i - X];
            if (maxSum == sum) {
                count++;
            } else if (maxSum < sum) {
                maxSum = sum;
                count = 1;
            }
        }

        if (maxSum == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(maxSum + "\n" + count);
        }
    }
}
