
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static boolean check(long mid) {
        int count = 0;
        long prev = 0;

        for (int i = 0; i < n; i++) {

            if (prev + mid > rocks[i]) { // 이전+최소거리보다 작은 섬은 제거
                count++;
            } else { // 최소거리 이상
                prev = rocks[i]; // 밟음
            }
        }

        if (d - prev < mid) {
            return false;
        }

        return count <= m; // 제거 수가 m개 이하면 길이를 더 늘려야함
    }

    static long d;
    static int n, m;
    static long[] rocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        d = Long.parseLong(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        rocks = new long[n];
        for (int i = 0; i < n; i++) {
            rocks[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(rocks);

        // 점프할 수 있는 최소거리의 최댓값 mid로 구하기
        long left = 1, right = d, result = 0;
        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (check(mid)) { // n-m개보다 더 밟았음
                result = mid;
                left = mid + 1; // 길이 늘려보기
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}
