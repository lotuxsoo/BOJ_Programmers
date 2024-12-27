import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int getCombination(HashMap<String, ArrayList<String>> map) {
        int ans = 1;
        for (String key : map.keySet()) {
            ans *= (map.get(key).size()+1);
        }

        return ans - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            HashMap<String, ArrayList<String>> map = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String value = st.nextToken();
                String key = st.nextToken();
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(value);
            }
            System.out.println(getCombination(map));
        }
    }
}