
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int boundStar(int i, int j) {
        int res = 0;
        for (int[] star : stars) {
            if (i <= star[0] && star[0] <= i + L && j <= star[1] && star[1] <= j + L) {
                res++;
            }
        }
        return res;
    }

    static int N, M, L, K;
    static ArrayList<int[]> stars = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new int[]{x, y});
        }

        // 별 좌표를 기준으로 트램펄린을 놓아야 TLE 안남
        int res = Integer.MIN_VALUE;
        for (int[] s1 : stars) {
            for (int[] s2 : stars) {
                res = Math.max(res, boundStar(s1[0], s2[1]));
            }
        }
        System.out.println(K - res);
    }
}
