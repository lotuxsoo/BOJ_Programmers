import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static boolean[] visited;
    static Set<String> set = new HashSet<>();
    static StringBuilder result = new StringBuilder();

    static void backtrack(int cnt, StringBuilder sb) {
        if (cnt == M) {
            if (!set.contains(sb.toString())) {
                set.add(new String(sb));
                result.append(new String(sb)).append("\n");
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                StringBuilder nsb = new StringBuilder(sb).append(arr[i]).append(" ");
                backtrack(cnt + 1, nsb);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        visited = new boolean[N];

        backtrack(0, new StringBuilder());

        System.out.println(result.toString());
    }
}
