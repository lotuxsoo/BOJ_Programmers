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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 정점의 개수
        int M = Integer.parseInt(br.readLine()); // 간선의 개수

        ArrayList<Node>[] A = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            A[s].add(new Node(e, v));
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[S] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.v, o2.v));
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.e]) {
                continue;
            }
            visited[cur.e] = true;

            for (Node next : A[cur.e]) {
                if (dist[next.e] > cur.v + next.v) {
                    dist[next.e] = cur.v + next.v;
                    pq.add(new Node(next.e, dist[next.e]));
                }
            }
        }

        System.out.println(dist[E]);
    }
}
