
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int findClosestCommonParent(int x, int y) {
        int d1 = depth[x], d2 = depth[y];
        int p1 = x, p2 = y;

        if (d1 != d2) {
            int sub = d1 - d2;
            if (sub < 0) {
                sub = -sub;
                while (sub-- > 0) {
                    p2 = parent[p2];
                }
            } else {
                while (sub-- > 0) {
                    p1 = parent[p1];
                }
            }
        }

        while (p1 != p2) {
            p1 = parent[p1];
            p2 = parent[p2];
        }

        return p1;
    }

    static void makeTree(int cur, int p, int d) {
        parent[cur] = p;
        depth[cur] = d;

        for (int next : tree[cur]) {
            if (next != p) {
                makeTree(next, cur, d + 1);
            }
        }
    }

    static ArrayList<Integer>[] tree;
    static int[] parent;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        parent = new int[N + 1];
        depth = new int[N + 1];

        makeTree(1, -1, 0);

        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findClosestCommonParent(a, b)).append("\n");
        }
        System.out.println(sb.toString());
    }
}
