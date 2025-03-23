
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static void dfs(int cnt, int usedMask) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((usedMask & (1 << i)) == 0) {
                answer[cnt] = i + 1;
                dfs(cnt + 1, usedMask | (1 << i));
            }
        }
    }

    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new int[M];
        dfs(0, 0);
        System.out.println(sb.toString());
    }
}
