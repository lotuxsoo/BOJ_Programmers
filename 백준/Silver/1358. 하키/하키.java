
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean isInLink(int x, int y) {
        // 직사각형 내부인지
        if (X <= x && x <= X + W && Y <= y && y <= Y + H) {
            return true;
        }

        // 왼쪽 반원 내부인지
        if (Math.pow(X - x, 2) + Math.pow(Y + H / 2 - y, 2) <= Math.pow(H / 2, 2)) {
            return true;
        }

        // 오른쪽 반원 내부인지
        if (Math.pow(X + W - x, 2) + Math.pow(Y + H / 2 - y, 2) <= Math.pow(H / 2, 2)) {
            return true;
        }

        return false;
    }

    static int W, H, X, Y, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int count = 0;

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (isInLink(x, y)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
