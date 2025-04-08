
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
                size[r2] += size[r1];
            } else if (rank[r1] > rank[r2]) {
                parent[r2] = r1;
                size[r1] += size[r2];
            } else {
                parent[r2] = r1;
                rank[r1]++;
                size[r1] += size[r2];
            }
        }
    }

    static int[] parent, rank, size;
    static final int MAX = 200000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int id = 0;
            Map<String, Integer> idMap = new HashMap<>();
            parent = new int[MAX];
            rank = new int[MAX];
            size = new int[MAX];
            for (int i = 0; i < MAX; i++) {
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
            }

            int F = Integer.parseInt(br.readLine());
            for (int i = 0; i < F; i++) {
                String[] sp = br.readLine().split(" ");
                idMap.putIfAbsent(sp[0], id++);
                idMap.putIfAbsent(sp[1], id++);
                int id1 = idMap.get(sp[0]);
                int id2 = idMap.get(sp[1]);

                if (find(id1) != find(id2)) {
                    union(id1, id2);
                }
                int root1 = find(id1);
                sb.append(size[root1] + "\n");
            }
        }
        System.out.println(sb.toString());
    }
}
