
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        L = Integer.parseInt(sp[1]);

        ArrayList<int[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            sp = br.readLine().split(" ");
            int s = Integer.parseInt(sp[0]);
            int e = Integer.parseInt(sp[1]);
            list.add(new int[]{s, e});
        }

        Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));

        int endIdx = 0;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            int[] cur = list.get(i);

            int length = cur[1] - Math.max(endIdx, cur[0]);
            if (length <= 0) {
                continue;
            }

            int cnt = length / L;
            if (length % L != 0) {
                cnt++;
            }
            answer += cnt;

            endIdx = Math.max(endIdx, cur[0]) + cnt * L;
        }

        System.out.println(answer);
    }
}
