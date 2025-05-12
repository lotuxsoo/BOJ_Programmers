
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");
            int s = Integer.parseInt(sp[0]);
            int e = Integer.parseInt(sp[1]);
            arr[i] = new int[]{s, e};
        }

        // 끝나는 시간 오름차순, 시작 시간 오름차순
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        // 끝나는 시간 담는 최소힙
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        pq.add(arr[0][1]); // 처음 끝나는 시간 담기

        for (int i = 1; i < N; i++) {
            if (!pq.isEmpty() && arr[i][0] >= pq.peek()) {
                pq.poll();
            }

            pq.add(arr[i][1]); // 끝나는 시간 담기
        }

        System.out.println(pq.size());
    }
}
