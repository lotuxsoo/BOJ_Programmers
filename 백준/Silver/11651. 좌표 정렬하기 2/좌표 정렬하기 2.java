
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int N;
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]);
            int y = Integer.parseInt(sp[1]);
            list.add(new int[]{x, y});
        }

        Collections.sort(list, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]); // y좌표 증가순
        });

        StringBuilder sb = new StringBuilder();
        for (int[] i : list) {
            sb.append(i[0] + " " + i[1] + "\n");
        }
        System.out.println(sb.toString());
    }
}
