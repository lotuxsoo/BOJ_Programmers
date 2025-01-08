import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        int left = 1;
        int right = 1;
        int cnt = 1;
        int sum = 1;

        while (left <= right && right < N) {
            if (sum == N) {
                cnt++;
                right++;
                sum += arr[right];
            } else if (sum < N) {
                right++;
                sum += arr[right];
            } else if (sum > N) {
                sum -= arr[left];
                left++;
            }
        }

        System.out.println(cnt);
    }
}
