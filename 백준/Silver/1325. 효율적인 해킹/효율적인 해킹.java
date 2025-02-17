import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // DFS 함수가 자기자신이 방문한 노드의 개수를 반환하도록 하자.
    static int DFS(int x) {
        visited[x] = true;
        int cnt = 1;

        for (int next : graph[x]) {
            if (!visited[next]) {
                cnt += DFS(next);
            }
        }

        return cnt;
    }

    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[B].add(A); // B를 해킹하면, A를 해킹할수있음
        }

        count = new int[N + 1];
        int MAX_COUNT = Integer.MIN_VALUE;

        // 각 노드에 대해 BFS 탐색
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                count[i]++;

                for (int next : graph[cur]) {
                    if (!visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }

            MAX_COUNT = Math.max(MAX_COUNT, count[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (count[i] == MAX_COUNT) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
