
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static boolean canInstall(long len) {
        long count = 1; // 첫번째 집은 무조건 설치
        int prev = home[0];

        for (int i = 1; i < N; i++) {
            if (prev + len <= home[i]) {
                count++;
                prev = home[i];
            }
        }
        return count >= C;
    }

    static int N, C;
    static int[] home;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        C = Integer.parseInt(sp[1]);
        home = new int[N];
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);
        long result = 0;

        // 공유기 사이의 최소 거리 이분탐색
        long low = 1, high = home[N - 1] - home[0];
        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (canInstall(mid)) {
                result = Math.max(result, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(result);
    }
}
