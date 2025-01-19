import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<String>[] A = new ArrayList[11]; // index: 1~10
        for (int i = 0; i < 11; i++) {
            A[i] = new ArrayList<>();
        }
        A[1].add("1");
        A[2].add("11");
        A[2].add("2");
        A[3].add("111");
        A[3].add("12");
        A[3].add("21");
        A[3].add("3");

        for (int i = 4; i < 11; i++) {
            for (int j = 1; j <= 3; j++) {
                ArrayList<String> a = A[i - j];
                for (String s : a) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(s).append(j);
                    A[i].add(sb.toString());
                }
            }
        }

        Collections.sort(A[n]);
        if (A[n].size() >= k) {
            StringBuilder sb = new StringBuilder();
            String[] splits = A[n].get(k - 1).split("");
            for (String s : splits) {
                sb.append(s).append("+");
            }
            System.out.println(sb.substring(0, sb.length() - 1));
        } else {
            System.out.println(-1);
        }
    }
}
