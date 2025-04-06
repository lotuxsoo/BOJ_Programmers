
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] subsequence = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            subsequence[i] = Integer.parseInt(st.nextToken());
        }

        // 투포인터 사용
        int left = 0, right = 0, sum = 0, minLen = 100000;
        while (left <= right) {

            while (sum < S && right < N) {
                sum += subsequence[right];
                right++;
            }

            if (sum < S) {
                break;
            }

            minLen = Math.min(minLen, right - left); // right는 이미 증가 상태
            sum -= subsequence[left];
            left++;
        }

        System.out.println(minLen == 100000 ? 0 : minLen);
    }
}
