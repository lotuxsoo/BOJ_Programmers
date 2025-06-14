
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static class Node {
        int to;
        long cost;

        Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static long solve(int from, int to) {
        Arrays.fill(dp, INF);
        dp[from] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));
        pq.add(new Node(from, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dp[cur.to]) {
                continue;
            }

            if (cur.to == to) {
                break;
            }

            for (Node next : graph[cur.to]) {
                if (dp[next.to] > dp[cur.to] + next.cost) {
                    dp[next.to] = dp[cur.to] + next.cost;
                    pq.add(new Node(next.to, dp[next.to]));
                }
            }
        }

        return dp[to];
    }

    static int N, E;
    static ArrayList<Node>[] graph;
    static long[] dp;
    static final long INF = 1_000_000_000_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        E = Integer.parseInt(sp[1]);

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            sp = br.readLine().split(" ");
            int a = Integer.parseInt(sp[0]);
            int b = Integer.parseInt(sp[1]);
            int c = Integer.parseInt(sp[2]);
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        sp = br.readLine().split(" ");
        int v1 = Integer.parseInt(sp[0]);
        int v2 = Integer.parseInt(sp[1]);

        long sum1 = 0, sum2 = 0;

        dp = new long[N + 1];

        sum1 += solve(1, v1) + solve(v1, v2) + solve(v2, N);
        sum2 += solve(1, v2) + solve(v2, v1) + solve(v1, N);

        if (sum1 >= INF && sum2 >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(sum1, sum2));
        }
    }
}
