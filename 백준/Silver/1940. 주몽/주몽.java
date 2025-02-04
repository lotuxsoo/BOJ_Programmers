import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken()); // 모두 양수
        }
        Arrays.sort(A);

        int answer = 0;
        int left = 0, right = N - 1; // 윈도우 범위 좁혀나감

        while (left < right) {
            long sum = A[left] + A[right];
            if (sum == M) {
                answer++;
                left++;
                right--;
            } else if (sum < M) {
                left++;
            } else if (sum > M) {
                right--;
            }
        }

        System.out.println(answer);
    }
}
