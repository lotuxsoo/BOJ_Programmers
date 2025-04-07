
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        Arrays.sort(arr);
        long closest = Long.MAX_VALUE;
        long[] result = new long[3];

        // 투포인터 + 이분탐색
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1, right = N - 1;

            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];
                if (Math.abs(closest) > Math.abs(sum)) {
                    closest = sum;
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];
                }
                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        Arrays.sort(result);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}
