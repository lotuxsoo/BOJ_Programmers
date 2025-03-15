
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        value = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = N - 1;
        int[] pair = new int[]{value[left], value[right]};

        while (left < right) {
            if (Math.abs(value[left] + value[right]) < Math.abs(pair[0] + pair[1])) {
                pair[0] = value[left];
                pair[1] = value[right];
            }

            if (value[left] + value[right] == 0) {
                break;
            } else if (value[left] + value[right] < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(pair[0] + " " + pair[1]);
    }
}
