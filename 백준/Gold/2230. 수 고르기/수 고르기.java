
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        if (M == 0 || N == 1) {
            System.out.println(0);
            return;
        }
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 투포인터
        Arrays.sort(arr);
        int left = 0, right = 1, min = Integer.MAX_VALUE;

        while (right < N) {
            int diff = arr[right] - arr[left];

            if (diff >= M) {
                min = Math.min(min, diff);
                left++;
            } else if (diff < M) {
                right++;
            }
        }

        System.out.println(min);
    }
}
