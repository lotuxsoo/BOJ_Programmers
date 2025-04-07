
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 정렬 O(NlogN) + 투포인터 O(N)
        Arrays.sort(arr);

        long closest = Long.MAX_VALUE;
        long[] result = new long[2];

        int left = 0, right = N - 1;
        while (left < right) {
            long sum = arr[left] + arr[right];

            if (Math.abs(closest) > Math.abs(sum)) {
                closest = sum;
                result[0] = arr[left];
                result[1] = arr[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(result[0] + " " + result[1]);
    }
}
