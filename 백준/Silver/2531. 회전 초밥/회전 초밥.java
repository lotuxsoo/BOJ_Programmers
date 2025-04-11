
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, d, k, c;
    static int[] rice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        rice = new int[N];
        for (int i = 0; i < N; i++) {
            rice[i] = Integer.parseInt(br.readLine());
        }

        // 빈도수 배열 저장
        int[] count = new int[d + 1];

        // 초기 윈도우 설정 (0~k-1)
        int dish = 0;
        for (int i = 0; i < k; i++) {
            if (count[rice[i]] == 0) {
                dish++;
            }
            count[rice[i]]++;
        }

        int result = dish + (count[c] == 0 ? 1 : 0);

        // 슬라이딩 윈도우 이동 (시작점을 1~N-1까지 이동, 0은 이미 확인)
        for (int i = 1; i < N; i++) {
            count[rice[i - 1]]--;
            if (count[rice[i - 1]] == 0) {
                dish--;
            }

            int newIdx = (i + k - 1) % N;
            if (count[rice[newIdx]] == 0) {
                dish++;
            }
            count[rice[newIdx]]++;

            result = Math.max(result, dish + (count[c] == 0 ? 1 : 0));
        }

        System.out.println(result);
    }
}
