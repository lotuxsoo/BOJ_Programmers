import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int a, b, c;

        Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static PriorityQueue<Edge> pq;
    static int N, M;
    static int[] parent;

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int r1 = find(a);
        int r2 = find(b);
        if (r1 != r2) {
            parent[r1] = r2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 노드 개수
        M = Integer.parseInt(br.readLine()); // 간선 개수

        pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.c));
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        int cost = 0;

        while (cnt < N - 1) {
            Edge cur = pq.poll();
            if (find(cur.a) != find(cur.b)) {
                union(cur.a, cur.b);
                cost += cur.c;
                cnt++;
            }

            if (cnt == N - 1) {
                break;
            }
        }

        System.out.println(cost);
    }
}
