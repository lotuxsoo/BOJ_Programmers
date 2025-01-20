import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int d(int num) {
        int ans = num;

        while (num > 0) {
            ans += num % 10;
            num /= 10;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; // = new StringTokenizer(br.readLine());
        // StringBuffer sb = new StringBuffer();

        // 10,000보다 작거나같은 셀프넘버 찾기
        boolean[] check = new boolean[10001];
        for (int num = 1; num <= 10000; num++) {
            int ans = d(num);
            if (ans <= 10000)
                check[ans] = true;
        }
        for (int i = 1; i <= 10000; i++) {
            if (!check[i]) {
                System.out.println(i);
            }
        }
    }
}
