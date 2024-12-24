import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, X;
    static Queue<Integer> que = new ArrayDeque<>();
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] dist;

    static void BFS(int start) {
        dist[start] = 0; // 시작노드
        que.offer(start);

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int next : list.get(now)) {
                if (dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    que.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 인접리스트 초기화
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        // 간선 인접리스트에 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list.get(A).add(B);
        }

        dist = new int[N + 1];
        Arrays.fill(dist, -1);

        BFS(X);

        boolean flag = true;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                System.out.println(i);
                flag = false;
            }
        }

        if (flag) {
            System.out.println(-1);
        }
    }
}
