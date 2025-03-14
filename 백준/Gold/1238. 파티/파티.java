
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int dest, time;

        Node(int dest, int time) {
            this.dest = dest;
            this.time = time;
        }
    }

    static int N, M, X;
    static ArrayList<Node>[] graph;
    static ArrayList<Node>[] reverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

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
            int t = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, t));
            reverse[s].add(new Node(e, t));
        }

        // 거리 저장
        int[] cost = new int[N + 1];
        int maxCost = 0;

        for (int i = 1; i <= N; i++) {
            if (i == X) {
                continue;
            }

            int[] dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[i] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));
            pq.add(new Node(i, dist[i]));

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (cur.dest == X) {
                    cost[i] += dist[cur.dest];
                    break;
                }
                if (dist[cur.dest] < cur.time) {
                    continue;
                }
                for (Node next : graph[cur.dest]) {
                    if (dist[next.dest] > dist[cur.dest] + next.time) {
                        dist[next.dest] = dist[cur.dest] + next.time;
                        pq.add(new Node(next.dest, dist[next.dest]));
                    }
                }
            }

            dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[X] = 0;

            pq = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));
            pq.add(new Node(X, 0));

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (cur.dest == i) {
                    cost[i] += dist[cur.dest];
                    maxCost = Math.max(maxCost, cost[i]);
                    break;
                }
                if (dist[cur.dest] < cur.time) {
                    continue;
                }
                for (Node next : reverse[cur.dest]) {
                    if (dist[next.dest] > dist[cur.dest] + next.time) {
                        dist[next.dest] = dist[cur.dest] + next.time;
                        pq.add(new Node(next.dest, dist[next.dest]));
                    }
                }
            }
        }

        System.out.println(maxCost);
    }
}
