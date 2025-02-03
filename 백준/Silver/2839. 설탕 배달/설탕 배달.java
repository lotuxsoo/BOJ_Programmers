import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int answer = 0;

        int five = N / 5;

        while (five >= 0) {
            int remain = N - five * 5;
            if (remain % 3 == 0) {
                five += remain / 3;
                System.out.println(five);
                return;
            }
            five--;
        }

        System.out.println(-1);
    }
}
