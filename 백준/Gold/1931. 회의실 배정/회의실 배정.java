import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Time implements Comparable<Time> {
        int start, end;

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            // 끝나는 시간 기준 오름차순, 시작 시간 오름차순
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }

    static int N;
    static ArrayList<Time> list = new ArrayList<>();
    static int MAX_VAL = Integer.MIN_VALUE;

    static void calc(int index, int last, int cnt) {
        if (index == N) {
            MAX_VAL = Math.max(MAX_VAL, cnt);
            return;
        }

        Time time = list.get(index);
        if (time.start >= last) {
            last = time.end;
            calc(index + 1, last, cnt + 1);
        } else {
            calc(index + 1, last, cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Time(start, end));
        }

        Collections.sort(list);

        calc(0, -1, 0);

        System.out.println(MAX_VAL);
    }
}