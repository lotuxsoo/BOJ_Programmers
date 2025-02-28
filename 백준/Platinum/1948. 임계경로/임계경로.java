
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int dst, cost;

        Node(int dst, int cost) {
            this.dst = dst;
            this.cost = cost;
        }
    }

    static ArrayList<Node>[] A; // 정방향 인접리스트
    static ArrayList<Node>[] R; // 역방향 인접리스트
    static int[] D; // 진입차수 배열
    static int n, m, start, end;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        A = new ArrayList[n + 1];
        R = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            A[i] = new ArrayList<>();
            R[i] = new ArrayList<>();
        }

        D = new int[n + 1];

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            A[s].add(new Node(e, v));
            R[e].add(new Node(s, v));
            D[e]++;
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // dp[u]: start->u까지 가는데 걸리는 최장 시간
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[start] = 0;

        // 임계경로 시간 구하기
        Queue<Node> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (D[i] == 0) {
                queue.add(new Node(i, 0));
            }
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (dp[cur.dst] == -1) {
                continue;
            }

            for (Node next : A[cur.dst]) {
                dp[next.dst] = Math.max(dp[next.dst], dp[cur.dst] + next.cost);

                D[next.dst]--;
                if (D[next.dst] == 0) {
                    queue.add(new Node(next.dst, dp[next.dst]));
                }
            }
        }

        System.out.println(dp[end]);

        int answer = 0;
        // 임계경로 간선 구하기
        Queue<Integer> q = new LinkedList<>();
        q.add(end);
        boolean[] visited = new boolean[n + 1];
        visited[end] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node next : R[cur]) {
                int dst = next.dst, cost = next.cost;
                if (dp[dst] + cost == dp[cur]) {
                    answer++;

                    if (!visited[dst]) {
                        visited[dst] = true;
                        q.add(dst);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
