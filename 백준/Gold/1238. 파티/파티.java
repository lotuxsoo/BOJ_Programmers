
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

    static int N, M, X;
    static ArrayList<Node>[] graph;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 도로 수
        X = Integer.parseInt(st.nextToken()); // 파티 장소

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, w));
        }

        int[][] distFromN = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(distFromN[i], INF);
            distFromN[i][i] = 0;
        }

        // 각 마을마다 -> X까지 가는 최단 경로
        for (int i = 1; i <= N; i++) {
            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
            pq.add(new Node(i, 0));

            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (distFromN[i][cur.val] < cur.cost) {
                    continue;
                }

                for (Node next : graph[cur.val]) {
                    if (distFromN[i][next.val] > distFromN[i][cur.val] + next.cost) {
                        distFromN[i][next.val] = distFromN[i][cur.val] + next.cost;
                        pq.add(new Node(next.val, distFromN[i][next.val]));
                    }
                }
            }
        }

        // X에서 -> 각 마을까지 가는 최단 경로
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Node(X, 0));

        int[] distFromX = new int[N + 1];
        Arrays.fill(distFromX, INF);
        distFromX[X] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distFromX[cur.val] < cur.cost) {
                continue;
            }

            for (Node next : graph[cur.val]) {
                if (distFromX[next.val] > distFromX[cur.val] + next.cost) {
                    distFromX[next.val] = distFromX[cur.val] + next.cost;
                    pq.add(new Node(next.val, distFromX[next.val]));
                }
            }
        }

        int maxTime = 0;

        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, distFromN[i][X] + distFromX[i]);
        }

        System.out.println(maxTime);
    }
}
