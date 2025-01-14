import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Day {
        int T; // 걸리는 기간
        int P; // 금액

        Day(int T, int P) {
            this.T = T;
            this.P = P;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Day> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            list.add(new Day(T, P));
        }

        int[] D = new int[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            Day d = list.get(i);
            if (i + d.T - 1 < N) {
                D[i] = Math.max(D[i + 1], D[i + d.T] + d.P);
            } else {
                D[i] = D[i + 1];
            }
        }

        Arrays.sort(D);
        System.out.println(D[D.length - 1]);
    }
}
