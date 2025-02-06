import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static class Edge {
        int s, e, cost;

        Edge(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        if (root1 != root2) {
            parent[root1] = root2;
        }
    }

    static int N; // 컴퓨터 개수
    static int[][] map;
    static PriorityQueue<Edge> pq;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int total = 0;
        pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));

        for (int i = 0; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (ch[j] == '0') {
                    continue;
                } else if (ch[j] >= 'A' && ch[j] <= 'Z') {
                    map[i][j] = ch[j] - 'A' + 27;
                    pq.add(new Edge(i, j, map[i][j]));
                } else if (ch[j] >= 'a' && ch[j] <= 'z') {
                    map[i][j] = ch[j] - 'a' + 1;
                    pq.add(new Edge(i, j, map[i][j]));
                }
                total += map[i][j];
            }
        }

        // 유니온 파인드 초기화
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        boolean[] visited = new boolean[N];
        int cost = 0;

        while ((cnt < N - 1) && !pq.isEmpty()) {
            Edge cur = pq.poll();

            if (find(cur.s) != find(cur.e)) {
                union(cur.s, cur.e);
                cost += cur.cost;
                cnt++;
            }
        }

        System.out.println((cnt == N - 1) ? total - cost : -1);
    }
}
