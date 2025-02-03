import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 시작시간 오름차순, 끝나는시간 오름차순
        PriorityQueue<int[]> pq1 = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            pq1.add(new int[]{S, T});
        }

        // 끝나는시간 오름차순
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        pq2.add(pq1.poll()[1]); // 첫번째로 끝나는시간 저장

        while (!pq1.isEmpty()) {
            int[] now = pq1.poll();
            if (now[0] >= pq2.peek()) {
                pq2.poll();
                pq2.add(now[1]); // 현재 끝나는시간 저장
            } else {
                pq2.add(now[1]);
            }
        }

        System.out.println(pq2.size());
    }
}
