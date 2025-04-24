
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int dest, cost;

        Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static int N;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) { // 1-index
            int W = Integer.parseInt(br.readLine());
            graph[0].add(new Edge(i, W));
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int P = Integer.parseInt(st.nextToken());
                graph[i].add(new Edge(j, P));
                graph[j].add(new Edge(i, P));
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));

        boolean[] visited = new boolean[N + 1];
        visited[0] = true;
        for (Edge next : graph[0]) {
            pq.add(next);
        }

        long result = 0;
        int edgeCount = 0;

        while (!pq.isEmpty() && edgeCount < N + 1) {
            Edge cur = pq.poll();

            if (visited[cur.dest]) {
                continue;
            }
            // 방문체크는 큐에서 뺄때
            visited[cur.dest] = true;
            result += cur.cost;
            edgeCount++;

            for (Edge next : graph[cur.dest]) {
                if (!visited[next.dest]) {
                    pq.add(next);
                }
            }
        }

        System.out.println(result);
    }
}
