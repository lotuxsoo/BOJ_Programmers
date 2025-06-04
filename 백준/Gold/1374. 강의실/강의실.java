
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ArrayList<int[]> lectures = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]);
            int s = Integer.parseInt(sp[1]);
            int e = Integer.parseInt(sp[2]);
            lectures.add(new int[]{x, s, e});
        }

        // 시작시간 -> 끝나는시간 오름차순
        Collections.sort(lectures, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[2], b[2]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // 끝나는 시간 최소 힙
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for (int i = 0; i < N; i++) {
            if (!pq.isEmpty()) {
                if (pq.peek() <= lectures.get(i)[1]) {
                    pq.poll();
                }
            }
            pq.add(lectures.get(i)[2]);
        }

        System.out.println(pq.size());
    }
}
