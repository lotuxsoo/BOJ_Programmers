
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static void initD(ArrayList<Integer> list) {

        for (int i = 0; i < list.size() - 1; i++) {
            int cur = list.get(i);
            int next = list.get(i + 1);
            A[cur].add(next);
            D[next]++;
        }
    }

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
            ArrayList<Integer> list = new ArrayList<>();
            int x = Integer.parseInt(st.nextToken());
            for (int j = 0; j < x; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            initD(list);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (D[i] == 0) {
                queue.add(i);
            }
        }

        int cnt = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            cnt++;
            sb.append(cur).append("\n");

            for (int next : A[cur]) {
                D[next]--;
                if (D[next] == 0) {
                    queue.add(next);
                }
            }
        }

        if (cnt < N) {
            System.out.println(0);
        } else {
            System.out.println(sb.toString().trim());
        }
    }
}
