
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int N;
    static PriorityQueue<Edge> pq;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        // 비용 작은순 오름차순 정렬
        pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        for (int i = 1; i <= N; i++) {
            int W = Integer.parseInt(br.readLine());
            graph[0].add(new Edge(i, W));
            pq.add(new Edge(i, W)); // 처음 우물파는 비용 전부 저장
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int P = Integer.parseInt(st.nextToken());
                graph[i].add(new Edge(j, P));
                graph[j].add(new Edge(i, P));
            }
        }

        boolean[] visited = new boolean[N + 1];
        long minCost = 0;
        int connected = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;
            minCost += cur.cost;
            connected++;

            if (connected == N) {
                break;
            }

            for (Edge next : graph[cur.to]) {
                if (!visited[next.to]) {
                    pq.add(next);
                }
            }
        }

        System.out.println(minCost);
    }
}
