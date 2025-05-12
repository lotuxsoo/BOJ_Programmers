
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        n = Integer.parseInt(sp[0]);
        m = Integer.parseInt(sp[1]);

        // 최소힙 생성
        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> Long.compare(a, b));

        sp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(sp[i]);
            pq.add(x);
        }

        long num = 0;
        while (num < m) {
            long a = pq.poll();
            long b = pq.poll();
            long x = a + b;
            pq.add(x);
            pq.add(x);
            num++;
        }

        long result = 0;
        while (!pq.isEmpty()) {
            result += pq.poll();
        }

        System.out.println(result);
    }
}
