import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 양수는 내림차순 정렬
        PriorityQueue<Integer> pos = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        // 음수는 오름차순 정렬 (절댓값 더 큰 순대로)
        PriorityQueue<Integer> neg = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x > 0) {
                pos.add(x);
            } else {
                neg.add(x);
            }
        }

        long result = 0;

        while (pos.size() > 1) {
            int a = pos.poll();
            int b = pos.poll();
            if (a == 1 || b == 1) { // 1은 곱하는거보다 더하는게 더 큼
                result += a + b;
            } else {
                result += a * b;
            }
        }
        if (!pos.isEmpty()) {
            result += pos.poll();
        }

        while (neg.size() > 1) {
            int a = neg.poll();
            int b = neg.poll();
            result += a * b;
        }
        if (!neg.isEmpty()) {
            result += neg.poll();
        }

        System.out.println(result);
    }
}
