import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void solve(String s, int num) {

        if (s.equals("add")) {
            mask |= (1 << num); // 추가
        } else if (s.equals("check")) {
            if ((mask & (1 << num)) != 0) { // 원소 없으면
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        } else if (s.equals("remove")) {
            mask &= ~(1 << num); // 제거

        } else if (s.equals("all")) {
            mask |= (1 << 20) - 1;
        } else if (s.equals("toggle")) {
            mask ^= (1 << num);
        } else if (s.equals("empty")) {
            mask = 0;
        }
    }

    static int mask = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] sp = br.readLine().split(" ");
            String s = sp[0];
            int num = 0;
            if (!s.equals("all") && !s.equals("empty")) {
                num = Integer.parseInt(sp[1]) - 1;
            }
            solve(s, num);
        }
        System.out.println(sb.toString());
    }
}
