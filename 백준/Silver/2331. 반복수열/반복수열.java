
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    static int A, P;
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        A = Integer.parseInt(sp[0]);
        P = Integer.parseInt(sp[1]);

        int idx = 0;
        map.put(A, idx);
        int start = -1;

        while (true) {
            idx++;

            // 각 자리의 숫자를 P번 곱한 수들의 합
            int key = 0;
            int temp = A;
            while (temp != 0) {
                int a = temp % 10;
                temp /= 10;
                int x = 1;
                for (int i = 0; i < P; i++) {
                    x = x * a;
                }
                key += x;
            }

            if (map.containsKey(key)) {
                start = map.get(key);
                break;
            } else {
                map.put(key, idx);
            }

            A = key;
        }

        System.out.println(start);
    }
}
