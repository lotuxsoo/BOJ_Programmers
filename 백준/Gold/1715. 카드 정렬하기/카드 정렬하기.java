import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 오름차순 큐
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int count = 0;

        while (pq.size() >= 2) {
            // 가장 작은 두숫자 poll
            int a = pq.poll();
            int b = pq.poll();
            // 두개 더해서 add
            pq.add(a + b);
            count += a + b;
            // pq가 정렬
        }

        System.out.println(count);
    }
}
