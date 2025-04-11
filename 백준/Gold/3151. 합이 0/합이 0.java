
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        long result = 0;

        for (int i = 0; i < N - 2; i++) { // i로 하나 고정
            int left = i + 1, right = N - 1;
            long sum = 0;

            while (left < right) {
                sum = A[i] + A[left] + A[right];

                if (sum == 0) {
                    if (A[left] == A[right]) {
                        int count = right - left + 1;
                        result += (long) count * (count - 1) / 2; // nC2 계산
                        break;
                    }

                    int lCount = 1, rCount = 1;

                    while (left + 1 < right && A[left] == A[left + 1]) {
                        lCount++;
                        left++;
                    }
                    
                    while (right - 1 > left && A[right] == A[right - 1]) {
                        rCount++;
                        right--;
                    }

                    result += (long) lCount * rCount;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                }
            }
        }

        System.out.println(result);
    }
}
