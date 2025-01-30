import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[] visited;
    static int[] sequence;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();
    static Set<String> set = new HashSet<>();

    static void backtrack(int depth) {
        if (depth == M) {
            if (!set.contains(Arrays.toString(sequence))) {
                set.add(Arrays.toString(sequence));
                for (int s : sequence) {
                    sb.append(s).append(" ");
                }
                sb.append("\n");
                return;
            }
        }

        int last = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && numbers[i] != last) {
                visited[i] = true;
                sequence[depth] = numbers[i];
                last = numbers[i];
                backtrack(depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N]; // N개의 자연수
        visited = new boolean[N];
        sequence = new int[M]; // M개의 수열

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        backtrack(0);

        System.out.println(sb.toString());
    }
}
