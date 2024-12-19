import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            long sum = 0;
            ArrayList<Integer> list = new ArrayList<>();
            while (st.hasMoreTokens()) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            for (int i = 0; i < t - 1; i++) {
                for (int j = i + 1; j < t; j++) {
                    sum += gcd(list.get(i), list.get(j));
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb.substring(0, sb.length() - 1).toString());
    }
}