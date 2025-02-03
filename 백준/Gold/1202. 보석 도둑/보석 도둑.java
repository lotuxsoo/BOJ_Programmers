import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int M, V;

        Node(int M, int V) {
            this.M = M; // 무게
            this.V = V; // 가격
        }

        @Override
        public int compareTo(Node o) {
            if (this.M == o.M) {
                return o.V - this.V;
            }
            return this.M - o.M;
        }
    }

    static int N, K; // 보석개수, 가방 개수
    static int[] C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // 무게
            int V = Integer.parseInt(st.nextToken()); // 가격
            nodes.add(new Node(M, V));
        }

        C = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            C[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(C); // 가방 무게 오름차순
        Collections.sort(nodes); // 보석 무게 오름차순, 가격 내림차순

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        long answer = 0;
        int idx = 0;

        for (int i = 0; i < K; i++) {

            while (idx < nodes.size()) {
                if (C[i] >= nodes.get(idx).M) {
                    pq.add(nodes.get(idx).V);
                    idx++;
                } else {
                    break;
                }
            }

            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        System.out.println(answer);
    }
}
