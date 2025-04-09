
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

    static int N, M, X;
    static ArrayList<Node>[] graph;
    static ArrayList<Node>[] reverse;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 도로 수
        X = Integer.parseInt(st.nextToken()); // 파티 장소

        graph = new ArrayList[N + 1];
        reverse = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, w)); // i->X
            reverse[e].add(new Node(s, w)); // X->i
        }

        // 역방향 그래프로 다른 마을->X까지의 최단거리 구함
        int[] revereDist = new int[N + 1];
        Arrays.fill(revereDist, INF);
        revereDist[X] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Node(X, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (revereDist[cur.val] < cur.cost) {
                continue;
            }

            for (Node next : reverse[cur.val]) {
                if (revereDist[next.val] > revereDist[cur.val] + next.cost) {
                    revereDist[next.val] = revereDist[cur.val] + next.cost;
                    pq.add(new Node(next.val, revereDist[next.val]));
                }
            }
        }

        // 순방향 그래프로 X->다른 마을까지의 최단거리 구함
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;

        pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Node(X, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.val] < cur.cost) {
                continue;
            }

            for (Node next : graph[cur.val]) {
                if (dist[next.val] > dist[cur.val] + next.cost) {
                    dist[next.val] = dist[cur.val] + next.cost;
                    pq.add(new Node(next.val, dist[next.val]));
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, revereDist[i] + dist[i]);
        }
        System.out.println(maxTime);
    }
}
