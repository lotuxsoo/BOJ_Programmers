
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int N;
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(br.readLine());
        }

        Collections.sort(list, (a, b) -> {
            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            return Integer.compare(a.length(), b.length());
        });

        StringBuilder sb = new StringBuilder();
        String prev = "";
        for (String s : list) {
            if (!s.equals(prev)) {
                sb.append(s).append("\n");
                prev = s;
            }
        }

        System.out.println(sb.toString());
    }
}
