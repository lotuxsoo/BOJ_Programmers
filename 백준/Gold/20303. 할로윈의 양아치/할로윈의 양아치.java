
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Group {
        int childCount, candySum;

        Group(int childCount, int candySum) {
            this.childCount = childCount;
            this.candySum = candySum;
        }
    }

    static void dfs(int cur, int sum) {

        for (int next : A[cur]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, sum);
            }
        }
    }

    static Group makeGroup(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int cnt = 0, sum = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            cnt++;
            sum += candy[cur];

            for (int next : A[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return new Group(cnt, sum);
    }

    static int N, M, K;
    static int[] candy;
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static ArrayList<Group> groups = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        candy = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
        }

        A = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a].add(b);
            A[b].add(a);
        }

        // 그룹 만들기
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                groups.add(makeGroup(i));
            }
        }

        // 배낭 문제 (0/1)
        int[] dp = new int[K];

        for (Group group : groups) {
            int childCount = group.childCount, candySum = group.candySum;
            if (childCount >= K) {
                continue;
            }
            for (int x = K-1; x >= childCount; x--) {
                dp[x] = Math.max(dp[x], dp[x - childCount] + candySum);
            }
        }

        System.out.println(dp[K-1]);
    }
}
