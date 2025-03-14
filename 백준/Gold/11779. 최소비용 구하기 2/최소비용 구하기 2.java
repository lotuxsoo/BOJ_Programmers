
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int val, cost;

        Node(int val, int cost) {
            this.val = val;
            this.cost = cost;
        }
    }

    static void printPath() {
        List<Integer> path = new ArrayList<>();
        int cur = e;

        while (cur != -1) {
            path.add(cur);
            cur = prev[cur];
        }

        System.out.println(path.size());
        Collections.reverse(path);
        StringBuilder sb = new StringBuilder();
        for (int p : path) {
            sb.append(p + " ");
        }
        System.out.println(sb.toString().trim());
    }

    static int n, m, s, e;
    static ArrayList<Node>[] graph;
    static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, c));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        prev = new int[n + 1];
        Arrays.fill(prev, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.val] < cur.cost) {
                continue;
            }

            for (Node next : graph[cur.val]) {
                if (dist[next.val] > dist[cur.val] + next.cost) {
                    dist[next.val] = dist[cur.val] + next.cost;
                    pq.add(new Node(next.val, dist[next.val]));
                    prev[next.val] = cur.val;
                }
            }
        }

        System.out.println(dist[e]);

        printPath();
    }
}
