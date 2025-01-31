import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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

    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수

        // 유지비 기준 오름차순
        pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.c, e2.c));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }

        // 유니온 파인드
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        int cnt = 0; // N-1 될때까지
        ArrayList<Integer> costList = new ArrayList<>();
        int answer = 0;

        while (cnt < N - 1) {
            Edge cur = pq.poll();

            if (find(cur.a) != find(cur.b)) {
                union(cur.a, cur.b);
                cnt++;
                costList.add(cur.c);
            }

            if (cnt == N - 1) {
                costList.sort(Collections.reverseOrder());
                int sum = costList.stream().mapToInt(i -> i).sum();
                answer = sum - costList.get(0);
                break;
            }
        }

        System.out.println(answer);
    }
}
