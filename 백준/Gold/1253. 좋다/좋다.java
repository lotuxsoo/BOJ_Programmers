
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static boolean isGood(int idx) {
        // 투포인터로 찾기
        long target = A[idx];
        int left = 0, right = N - 1;
        while (left < right) {
            if (left == idx) {
                left++;
                continue;
            } else if (right == idx) {
                right--;
                continue;
            }

            long sum = A[left] + A[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return true;
            }
        }
        return false;
    }

    static int N;
    static long[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(A);

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (isGood(i)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
