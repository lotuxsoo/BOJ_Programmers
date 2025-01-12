import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int first = Math.abs(o1);
            int second = Math.abs(o2);
            if (first == second) { // 절댓값이 같으면 음수 먼저 정렬
                return o1 < o2 ? -1 : 1;
            } else {
                return first - second; // 절댓값이 작은 순으로 정렬
            }
        });

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
            if (A[i] == 0) {
                // 빈배열이면 0 출력
                if (!pq.isEmpty()) {
                    System.out.println(pq.poll());
                } else {
                    System.out.println(0);
                }
            } else {
                pq.add(A[i]);
            }
        }

    }
}
