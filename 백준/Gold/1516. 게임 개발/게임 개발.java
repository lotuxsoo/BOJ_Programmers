import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] graph;
    static int[] indegree, cost, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        indegree = new int[N + 1]; // 진입차수 배열
        cost = new int[N + 1]; // 각 건물의 건설 시간
        answer = new int[N + 1]; // 각 건물의 완료 시간

        graph = new ArrayList[N + 1]; // 인접리스트 배열
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().replace("-1", ""));
            cost[i] = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken()); // 선행 건물
                graph[x].add(i); // x -> i
                indegree[i]++; // 진입차수 증가
            }
        }

        // 위상정렬
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                answer[i] = cost[i];
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                indegree[next]--;
                answer[next] = Math.max(answer[next], answer[cur] + cost[next]);

                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            System.out.println(answer[i]);
        }

    }
}
