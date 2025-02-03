import java.io.*;

public class Main {

    static int[] time = {300, 60, 10}; // 5분, 1분, 10초 버튼

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 🚨 10초 단위가 아니면 -1 출력
        if (T % 10 != 0) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int t : time) {
            sb.append(T / t).append(" ");
            T %= t;
        }

        System.out.println(sb.toString().trim());
    }
}