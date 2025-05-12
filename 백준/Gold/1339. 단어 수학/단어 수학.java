
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static int N;
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            int x = 1;
            for (int j = ch.length - 1; j >= 0; j--) {
                map.put(ch[j], map.getOrDefault(ch[j], 0) + x);
                x *= 10;
            }
        }

        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());
        // 자릿수 영향력 기준 내림차순 정렬
        Collections.sort(entryList, (a, b) -> Integer.compare(b.getValue(), a.getValue()));

        long result = 0;
        int cur = 9;
        for (Map.Entry<Character, Integer> entry : entryList) {
            result += entry.getValue() * cur;
            cur--;
        }

        System.out.println(result);
    }
}
