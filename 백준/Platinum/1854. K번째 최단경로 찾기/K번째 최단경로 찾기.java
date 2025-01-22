import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int e, v;

        Node(int e, int v) {
            this.e = e; // 목적지 노드
            this.v = v; // 비용
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 노드 개수
        int m = Integer.parseInt(st.nextToken()); // 에지 개수
        int k = Integer.parseInt(st.nextToken()); // k번째 최단경로

        // 인접 리스트 생성
        ArrayList<Node>[] A = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            A[a].add(new Node(b, c));
        }

        // 각 노드의 최단 경로를 저장하는 우선순위 큐
        PriorityQueue<Integer>[] distQueue = new PriorityQueue[n + 1];
        for (int i = 0; i <= n; i++) {
            distQueue[i] = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1)); // 최대힙
        }

        // 우선순위 큐로 다익스트라 탐색
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.v, o2.v));
        pq.add(new Node(1, 0));
        distQueue[1].add(0); // 출발점 비용 0

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next : A[cur.e]) {
                int nextDist = cur.v + next.v;

                // 현재 노드까지의 경로가 K개 이하라면 추가
                if (distQueue[next.e].size() < k) {
                    distQueue[next.e].add(nextDist);
                    pq.add(new Node(next.e, nextDist));
                }
                // 이미 K개 저장된 경우, 가장 큰 값보다 작을 때만 갱신
                else if (distQueue[next.e].peek() > nextDist) {
                    distQueue[next.e].poll();
                    distQueue[next.e].add(nextDist);
                    pq.add(new Node(next.e, nextDist));
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (distQueue[i].size() < k) {
                sb.append(-1).append("\n");
            } else {
                sb.append(distQueue[i].peek()).append("\n");
            }
        }
        System.out.print(sb);
    }
}