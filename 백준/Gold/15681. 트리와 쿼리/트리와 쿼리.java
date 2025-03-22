
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int makeTree(int cur, int parent) {
        visited[cur] = true;
        subtrees[cur] = 1;

        if (tree[cur].isEmpty()) {
            return subtrees[cur];
        }

        for (int next : tree[cur]) {
            if (!visited[next] && parent != next) {
                subtrees[cur] += makeTree(next, cur);
            }
        }

        return subtrees[cur];
    }

    static int N, R, Q;
    static ArrayList<Integer>[] tree;
    static int[] subtrees;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        subtrees = new int[N + 1];
        visited = new boolean[N + 1];

        makeTree(R, -1);

        for (int i = 0; i < Q; i++) {
            int U = Integer.parseInt(br.readLine());
            System.out.println(subtrees[U]);
        }
    }
}
