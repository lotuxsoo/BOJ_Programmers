
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int countWords(int mask) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if ((mask & wordMask[i]) == wordMask[i]) {
                cnt++;
            }
        }
        return cnt;
    }

    static void dfs(int cnt, int start, int mask) {
        if (cnt == K - 5) {
            MAX = Math.max(MAX, countWords(mask));
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((mask & (1 << i)) == 0) { // 아직 선택되지 않은 알파벳이면
                dfs(cnt + 1, i + 1, mask | (1 << i));
            }
        }
    }

    static int N, K;
    static int[] wordMask;
    static char[] chars = {'a', 'n', 't', 'i', 'c'};
    static int MAX = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        K = Integer.parseInt(sp[1]);

        wordMask = new int[N];
        for (int i = 0; i < N; i++) {
            int mask = 0;
            String word = br.readLine();
            for (int j = 0; j < word.length(); j++) {
                mask |= (1 << word.charAt(j) - 'a');
            }
            wordMask[i] = mask;
        }

        if (K < 5) {
            System.out.println(0);
            return;
        }

        int mask = 0;
        for (char c : chars) {
            mask |= (1 << c - 'a');
        }

        dfs(0, 0, mask);

        System.out.println(MAX);
    }
}
