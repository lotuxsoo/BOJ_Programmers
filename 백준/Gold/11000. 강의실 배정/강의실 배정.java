import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Lecture {
        int S, T;

        Lecture(int S, int T) {
            this.S = S;
            this.T = T;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Lecture> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            list.add(new Lecture(S, T));
        }

        // 시작시간 오름차순 정렬
        Collections.sort(list, (a, b) -> Integer.compare(a.S, b.S));

        // 끝나는 시간 오름차순 poll
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(list.get(0).T);

        for (int i = 1; i < list.size(); i++) {
            Lecture now = list.get(i);
            if (now.S >= pq.peek()) {
                pq.poll();
                pq.add(now.T);
            } else {
                pq.add(now.T);
            }
        }

        int answer = pq.size();
        System.out.println(answer);

    }
}
