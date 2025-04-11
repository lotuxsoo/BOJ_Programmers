
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M;
    static int[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);
        long total = 0, max = 0;
        used = new int[N];
        for (int i = 0; i < N; i++) {
            used[i] = Integer.parseInt(br.readLine());
            total += used[i];
            max = Math.max(max, used[i]);
        }

        long left = max, right = total, K = 0;
        while (left <= right) {
            long mid = left + (right - left) / 2;

            long money = mid, count = 1;
            for (int u : used) {
                if (money >= u) {
                    money -= u;
                } else {
                    money = mid - u;
                    count++;
                }
            }

            if (count <= M) { // 인출한 횟수가 적었으면 금액 줄이기
                K = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(K);
    }
}
