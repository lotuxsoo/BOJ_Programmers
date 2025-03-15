
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] value = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            value[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(value);

        long minSum = Long.MAX_VALUE;
        long[] arr = new long[3];

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1, right = N - 1;

            while (left < right) {
                long temp = value[i] + value[left] + value[right];
                if (Math.abs(temp) < Math.abs(minSum)) {
                    minSum = temp;
                    arr[0] = value[i];
                    arr[1] = value[left];
                    arr[2] = value[right];
                }

                if (value[i] + value[left] + value[right] == 0) {
                    break;
                } else if (value[i] + value[left] + value[right] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
    }
}
