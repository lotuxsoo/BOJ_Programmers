import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int left = 1, right = 1;
        int count = 0;
        int sum = left;

        while (left <= right && right <= N) {
            if (sum == N) {
                count++;
                sum -= left;
                left++;
            } else if (sum > N) {
                sum -= left;
                left++;
            } else if (sum < N) {
                right++;
                sum += right;
            }
        }

        System.out.println(count);
    }
}
