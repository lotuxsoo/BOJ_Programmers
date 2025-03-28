
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int dest;
        long cost;

        Node(int dest, long cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static int N, E;
    static ArrayList<Node>[] graph;
    static final long INF = 1_000_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int V1 = Integer.parseInt(st.nextToken());
        int V2 = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));

        // 1-> 모든 정점까지의 최단거리
        long[] dist1 = new long[N + 1];
        Arrays.fill(dist1, INF);
        dist1[1] = 0;
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist1[cur.dest] < cur.cost) {
                continue;
            }

            for (Node next : graph[cur.dest]) {
                if (dist1[next.dest] > next.cost + cur.cost) {
                    dist1[next.dest] = next.cost + cur.cost;
                    pq.add(new Node(next.dest, dist1[next.dest]));
                }
            }
        }

        pq.clear();
        long[] dist2 = new long[N + 1];
        Arrays.fill(dist2, INF);
        dist2[V1] = 0;
        pq.add(new Node(V1, 0));
        // V1 -> 모든 정점까지의 최단거리
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist2[cur.dest] < cur.cost) {
                continue;
            }

            for (Node next : graph[cur.dest]) {
                if (dist2[next.dest] > next.cost + cur.cost) {
                    dist2[next.dest] = next.cost + cur.cost;
                    pq.add(new Node(next.dest, dist2[next.dest]));
                }
            }
        }

        // V2 -> 모든 정점까지의 최단거리
        pq.clear();
        long[] dist3 = new long[N + 1];
        Arrays.fill(dist3, INF);
        dist3[V2] = 0;
        pq.add(new Node(V2, 0));
        // V1 -> 모든 정점까지의 최단거리
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist3[cur.dest] < cur.cost) {
                continue;
            }

            for (Node next : graph[cur.dest]) {
                if (dist3[next.dest] > next.cost + cur.cost) {
                    dist3[next.dest] = next.cost + cur.cost;
                    pq.add(new Node(next.dest, dist3[next.dest]));
                }
            }
        }

        long path1 = dist1[V1] + dist2[V2] + dist3[N];
        long path2 = dist1[V2] + dist3[V1] + dist2[N];

        if (dist1[V1] == INF || dist2[V2] == INF || dist3[N] == INF) {
            path1 = INF;
        }
        if (dist1[V2] == INF || dist3[V1] == INF || dist2[N] == INF) {
            path2 = INF;
        }

        long result = Math.min(path1, path2);

        System.out.println(result == INF ? -1 : result);
    }
}
