
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void dfs(int idx, int depth, int usedMask) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < N; i++) {
            if ((usedMask & (1 << i)) == 0) {
                answer[depth] = i + 1;
                dfs(i + 1, depth + 1, usedMask | (1 << i));
            }
        }
    }

    static int N, M;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new int[M];
        dfs(0, 0, 0);
        System.out.println(sb.toString());
    }
}
