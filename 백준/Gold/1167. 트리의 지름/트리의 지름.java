
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static int V;
    static ArrayList<Node>[] graph;

    static int[] bfs(int start) {
        int maxIdx = 0;
        int maxDist = 0;

        int[] dist = new int[V + 1];
        Arrays.fill(dist, -1);
        dist[start] = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (dist[cur.idx] < cur.cost) {
                continue;
            }

            for (Node next : graph[cur.idx]) {
                if (dist[next.idx] == -1) {
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    if (maxDist < dist[next.idx]) {
                        maxDist = dist[next.idx];
                        maxIdx = next.idx;
                    }
                    queue.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        return new int[]{maxIdx, maxDist};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        graph = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            while (true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());
                graph[idx].add(new Node(next, cost));
            }
        }

        // 1) 임의 노드(1번 등)에서 BFS → 가장 먼 노드 X 찾기
        int[] first = bfs(1);

        // 2) X에서 다시 BFS → 가장 먼 노드 Y와의 거리 = 트리 지름
        int[] second = bfs(first[0]);

        System.out.println(second[1]);
    }
}
