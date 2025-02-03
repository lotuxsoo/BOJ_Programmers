import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int sum(String a) {
        int sum = 0;
        String[] sp = a.split("[+]");
        for (String s : sp) {
            sum += Integer.parseInt(s);
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        String[] sp = br.readLine().split("-");
        for (int i = 0; i < sp.length; i++) {
            int temp = sum(sp[i]);
            if (i == 0) {
                answer += temp;
            } else {
                answer -= temp;
            }
        }

        System.out.println(answer);
    }
}
