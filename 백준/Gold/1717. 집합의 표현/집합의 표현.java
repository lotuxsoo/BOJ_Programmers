
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int r1 = find(x), r2 = find(y);
        if (r1 != r2) {
            if (rank[r1] < rank[r2]) {
                parent[r1] = r2;
            } else if (rank[r1] > rank[r2]) {
                parent[r2] = r1;
            } else {
                parent[r2] = r1;
                rank[r1]++;
            }
        }
    }

    static int n, m;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int mode = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (mode == 0) {
                if (find(a) != find(b)) {
                    union(a, b);
                }
            } else {
                if (find(a) != find(b)) {
                    sb.append("NO\n");
                } else {
                    sb.append("YES\n");
                }
            }
        }

        System.out.println(sb.toString());
    }
}
