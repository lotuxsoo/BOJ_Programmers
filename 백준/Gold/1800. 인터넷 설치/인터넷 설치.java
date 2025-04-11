
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int val, cost;

        Node(int val, int cost) {
            this.val = val;
            this.cost = cost;
        }
    }

    // mid 초과 간선을 가장 적게 쓰는 경로를 찾아서,
    // N번에 도달할 때 mid 초과 간선을 몇개 썼는지 확인하는 다익스트라
    static boolean check(int mid) {
        // 해당 번호까지 오면서 사용한 mid 초과 간선들
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.val] < cur.cost) {
                continue; // 이미 더 적게 사용해서 왔으면 패스
            }

            for (Node next : graph[cur.val]) {
                int cost = next.cost > mid ? cur.cost + 1 : cur.cost; // mid 초과하면 하나 추가
                if (dist[next.val] > cost) {
                    dist[next.val] = cost; // mid 초과 간선을 더 적게쓴 경로로 갱신
                    pq.add(new Node(next.val, cost));
                }
            }
        }

        return dist[N] <= K; // 사용 가능
    }

    static int N, P, K;
    static ArrayList<Node>[] graph;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int max = 0;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
            max = Math.max(max, w);
        }

        // 가장 비싼 케이블선이 right
        int left = 0, right = max, result = INF;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (check(mid)) {
                result = Math.min(result, mid);
                right = mid - 1; // mid 더 줄여보기
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result == INF ? -1 : result);
    }
}
