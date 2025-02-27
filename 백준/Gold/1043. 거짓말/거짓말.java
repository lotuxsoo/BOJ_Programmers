
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static void union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);

        if (root1 != root2) {
            parent[root1] = root2;
        }
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void getUnion(List<Integer> party) {
        int p = find(party.get(0));
        for (int i = 1; i < party.size(); i++) {
            if (p != find(party.get(i))) {
                union(p, find(party.get(i)));
            }
        }
    }

    static boolean canLie(int p, Set<Integer> knowers) {
        for (int x : knowers) {
            if (find(x) == p) {
                return false;
            }
        }
        return true;
    }

    static int N, M;
    static int[] parent;
    static Set<Integer> knowers = new HashSet<>();
    static List<List<Integer>> parties = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            knowers.add(Integer.parseInt(st.nextToken()));
        }

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            parties.add(new ArrayList<>()); // M개의 리스트 추가
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                parties.get(i).add(Integer.parseInt(st.nextToken()));
            }
            // 파티마다 union
            getUnion(parties.get(i));
        }

        int answer = 0;

        for (int i = 0; i < M; i++) {
            int p = find(parties.get(i).get(0));
            if (canLie(p, knowers)) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
