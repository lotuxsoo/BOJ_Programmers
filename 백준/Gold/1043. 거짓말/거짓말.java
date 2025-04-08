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

    static int makeNetwork(int[] people) {
        int prev = find(people[0]);
        for (int i = 1; i < people.length; i++) {
            int root = find(people[i]);
            if (prev != root) {
                union(prev, root);
            }
        }
        return prev;
    }

    static int[] parent, rank;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int[] truth = new int[num];
        if (num == 0) {
            System.out.println(M); // 모든 파티 거짓말 가능
            return;
        }
        for (int i = 0; i < num; i++) {
            truth[i] = Integer.parseInt(st.nextToken());
        }
        int truthNetwork = makeNetwork(truth);

        int[] network = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            int[] party = new int[num];
            for (int j = 0; j < num; j++) {
                party[j] = Integer.parseInt(st.nextToken());
            }
            network[i] = makeNetwork(party);
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            int root = find(network[i]);
            if (root != find(truthNetwork)) {
                result++;
            }
        }

        System.out.println(result);
    }
}
