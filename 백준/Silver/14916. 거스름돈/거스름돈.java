import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; // = new StringTokenizer(br.readLine());
        // StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        if (n % 5 == 0) {
            answer = n / 5;
            System.out.println(answer);
            return;
        }
        while (true) {
            n -= 2;
            answer++;
            if (n % 5 == 0) {
                answer += n / 5;
                System.out.println(answer);
                return;
            }
            if (n < 0) {
                System.out.println(-1);
                return;
            }
        }
    }
}