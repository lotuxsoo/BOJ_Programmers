import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] tree = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        // 나무 길이 오름차순 정렬
        Arrays.sort(tree);

        long low = 0, high = tree[N - 1], maxLen = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long len = 0; // 절단기 설정 높이
            for (int t : tree) {
                len += Math.max(0, t - mid);
            }
            if (len >= M) {
                maxLen = Math.max(maxLen, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(maxLen);
    }
}
