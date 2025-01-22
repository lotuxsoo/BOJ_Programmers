import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 노드 개수
        int m = Integer.parseInt(st.nextToken()); // 에지 개수
        int k = Integer.parseInt(st.nextToken()); // k번째 최단경로

        ArrayList<Node>[] A = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            A[a].add(new Node(b, c));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.v, o2.v));
        pq.add(new Node(1, 0));

        PriorityQueue<Integer>[] distQ = new PriorityQueue[n + 1];
        for (int i = 0; i < n + 1; i++) {
            distQ[i] = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        }

        distQ[1].add(0);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next : A[cur.e]) {
                int cost = cur.v + next.v;

                if (distQ[next.e].size() < k) {
                    distQ[next.e].add(cost);
                    pq.add(new Node(next.e, cost));
                } else {
                    if (distQ[next.e].peek() > cost) {
                        distQ[next.e].poll();
                        distQ[next.e].add(cost);
                        pq.add(new Node(next.e, cost));
                    }
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (distQ[i].size() < k) {
                System.out.println(-1);
                continue;
            }
            System.out.println(distQ[i].poll());
        }
    }
}
