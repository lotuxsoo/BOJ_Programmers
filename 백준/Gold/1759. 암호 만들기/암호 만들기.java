
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static void backtrack(int cnt, int vowelCnt, int otherCnt, int idx, StringBuilder sb) {
        if (cnt == L) {
            if (vowelCnt >= 1 && otherCnt >= 2) {
                System.out.println(sb.toString());
            }
            return;
        }

        for (int i = idx; i < C; i++) {
            sb.append(A[i]);
            if (vowels.contains(A[i])) {
                backtrack(cnt + 1, vowelCnt + 1, otherCnt, i + 1, sb);
            } else {
                backtrack(cnt + 1, vowelCnt, otherCnt + 1, i + 1, sb);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    static int L, C;
    static char[] A;
    static Set<Character> vowels = new HashSet<>(List.of('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            A[i] = st.nextToken().charAt(0);
        }

        // 백트래킹 전에 정렬, 자동으로 사전순 조합 생성
        Arrays.sort(A);

        backtrack(0, 0, 0, 0, new StringBuilder());
    }
}
