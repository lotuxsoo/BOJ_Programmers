
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int val, amount;

        Node(int val, int amount) {
            this.val = val;
            this.amount = amount;
        }
    }

    static int N, M;
    static ArrayList<Node>[] graph;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            // x를 만들기 위해 y가 k개 필요하다
            graph[y].add(new Node(x, k));
            indegree[x]++;
        }

        int[][] dp = new int[N + 1][N + 1];

        ArrayList<Integer> original = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                dp[i][i] = 1;
                original.add(i);
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Node next : graph[cur]) {
                if (original.contains(cur)) {
                    dp[next.val][cur] += next.amount;
                } else {
                    for (int i = 1; i <= N; i++) {
                        dp[next.val][i] += dp[cur][i] * next.amount;
                    }
                }

                indegree[next.val]--;
                if (indegree[next.val] == 0) {
                    queue.add(next.val);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int x : original) {
            sb.append(x).append(" ").append(dp[N][x]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
