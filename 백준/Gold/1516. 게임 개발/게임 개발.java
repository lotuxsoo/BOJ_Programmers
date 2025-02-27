
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N;
    static ArrayList<Integer>[] A;
    static int[] D;
    static int[] cost;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        D = new int[N + 1];
        cost = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            String[] splits = br.readLine().split(" ");
            cost[i] = Integer.parseInt(splits[0]);

            for (int j = 1; j < splits.length; j++) {
                int x = Integer.parseInt(splits[j]);
                if (x == -1) {
                    break;
                }
                A[x].add(i); // 선->후
                D[i]++; // 진입차수 추가
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        answer = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (D[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : A[cur]) {
                D[next]--;
                answer[next] = Math.max(answer[next], answer[cur] + cost[cur]);
                if (D[next] == 0) {
                    queue.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(cost[i] + answer[i]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
