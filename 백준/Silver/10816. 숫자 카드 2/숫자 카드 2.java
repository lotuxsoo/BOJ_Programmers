import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(st.nextToken());
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            if (!map.containsKey(i)) {
                sb.append(0).append(" ");
            } else {
                sb.append(map.get(i)).append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}