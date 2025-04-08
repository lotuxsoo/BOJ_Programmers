import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    static int V, E;
    static int[] parent;
    static PriorityQueue<Edge> pq;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.c, e2.c));
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            pq.add(new Edge(A, B, C));
        }

        parent = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            parent[i] = i;
        }

        int sum = 0;
        int cnt = 0;

        while (cnt < V - 1) {
            Edge cur = pq.poll();

            if (find(cur.a) != find(cur.b)) {
                union(cur.a, cur.b);
                sum += cur.c;
                cnt++;
            }

            if (cnt == V - 1) {
                break;
            }
        }

        System.out.println(sum);
    }
}
