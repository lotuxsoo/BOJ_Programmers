import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] moneys = {25, 10, 5, 1};

    static int[] get(int C) {
        int[] answer = new int[4];

        for (int i = 0; i < 4; i++) {
            if (C >= moneys[i]) {
                answer[i] = C / moneys[i];
                C = C % moneys[i];
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int C = Integer.parseInt(br.readLine());
            int[] ints = get(C);
            for (int x : ints) {
                System.out.print(x + " ");
            }
        }
    }
}
