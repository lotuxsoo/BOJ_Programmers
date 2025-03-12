
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static class Group {
        int childCount, candySum;

        Group(int childCount, int candySum) {
            this.childCount = childCount;
            this.candySum = candySum;
        }

        public void addChild() {
            this.childCount += 1;
        }

        public void addCandy(int candy) {
            this.candySum += candy;
        }
    }

    static void union(int x, int y) {
        int root1 = find(x), root2 = find(y);
        if (root1 != root2) {
            if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }
        }
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static Group makeGroup(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int cnt = 1, sum = candy[start];

        for (int i = 1; i <= N; i++) {
            if ((i != start) && !visited[i] && (find(start) == find(i))) {
                cnt++;
                sum += candy[i];
                visited[i] = true;
            }
        }

        return new Group(cnt, sum);
    }

    static int N, M, K;
    static int[] candy;
    static boolean[] visited;
    static ArrayList<Group> groups = new ArrayList<>();
    static int[] parent;
    static int[] rank;

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

        parent = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        visited = new boolean[N + 1];

        Map<Integer, Group> groupMap = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }

            int root = find(i);
            groupMap.putIfAbsent(root, new Group(0, 0));

            groupMap.get(root).addChild();
            groupMap.get(root).addCandy(candy[i]);
            visited[i] = true;
        }

        for (Group group : groupMap.values()) {
            groups.add(group);
        }

        int[] dp = new int[K];

        for (Group group : groups) {
            int childCount = group.childCount, candySum = group.candySum;

            for (int j = K - 1; j >= childCount; j--) {
                dp[j] = Math.max(dp[j], dp[j - childCount] + candySum);
            }
        }

        System.out.println(dp[K - 1]);
    }
}
