import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int e, v;

        Node(int e, int v) {
            this.e = e;
            this.v = v;
        }
    }

    static ArrayList<Node>[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        A = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            A[i] = new ArrayList<>();
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            A[s].add(new Node(e, v));
        }

        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[K] = 0; // 출발 노드 지정

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.v, o2.v));
        pq.add(new Node(K, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next : A[cur.e]) {

                if (dist[next.e] > cur.v + next.v) {
                    dist[next.e] = cur.v + next.v;
                    pq.add(new Node(next.e, dist[next.e]));
                }
            }
        }

        for (int i = 1; i < V + 1; i++) {
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }
    }
}
