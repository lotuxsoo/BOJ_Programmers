
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.sound.sampled.Line;

public class Main {

    static int N;
    static ArrayList<int[]> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            lines.add(new int[]{A, B});
        }

        // A 기준 오름차순 정렬
        Collections.sort(lines, (x, y) -> Integer.compare(x[0], y[0]));

        // dp[i]: i번째를 포함하는 가장 긴 증가/감소 부분수열
        int[] increase = new int[N];
        Arrays.fill(increase, 1);
        int maxIncrease = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (lines.get(j)[1] < lines.get(i)[1]) {
                    increase[i] = Math.max(increase[i], increase[j] + 1);
                }
            }
            maxIncrease = Math.max(maxIncrease, increase[i]);
        }

        System.out.println(N - maxIncrease);
    }
}
