import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int five = N / 5;

        while (five >= 0) {
            int remainder = N - (five * 5);
            if (remainder % 3 != 0) {
                five--;
            } else {
                five += remainder / 3;
                System.out.println(five);
                return;
            }
        }

        System.out.println(-1);
    }
}
