
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] A;
    static int[] D;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        D = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a].add(b);
            D[b]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        for (int i = 1; i <= N; i++) {
            if (D[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();

            sb.append(cur).append(" ");

            for (int next : A[cur]) {
                D[next]--;
                if (D[next] == 0) {
                    pq.add(next);
                }
            }
        }

        System.out.println(sb.toString().trim());
    }
}
