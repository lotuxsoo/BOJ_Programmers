import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        double x, y;
        int idx;

        Node(double x, double y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }

    static class Edge {
        Node a, b;
        double dist;

        Edge(Node a, Node b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int r1 = find(x);
        int r2 = find(y);
        if (r1 != r2) {
            parent[r1] = r2;
        }
    }

    static ArrayList<Node> nodes = new ArrayList<>();
    static PriorityQueue<Edge> pq;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            nodes.add(new Node(x, y, i));
        }

        pq = new PriorityQueue<>((e1, e2) -> Double.compare(e1.dist, e2.dist));

        // 간선들 구하기
        for (int i = 0; i < nodes.size() - 1; i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                Node n1 = nodes.get(i);
                Node n2 = nodes.get(j);
                double x1 = n1.x, y1 = n1.y, x2 = n2.x, y2 = n2.y;
                double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                pq.add(new Edge(n1, n2, distance));
            }
        }

        // 유니온 파인드
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        double total = 0;

        while (cnt < N - 1) {
            Edge cur = pq.poll();

            Node a = cur.a;
            Node b = cur.b;
            if (find(a.idx) != find(b.idx)) {
                union(a.idx, b.idx);
                cnt++;
                total += cur.dist;
            }
        }

        System.out.printf("%.2f", total);
    }
}
