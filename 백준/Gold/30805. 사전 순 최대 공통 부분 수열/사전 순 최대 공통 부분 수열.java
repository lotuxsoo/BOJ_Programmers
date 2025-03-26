
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> A = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }
        int M = Integer.parseInt(br.readLine());
        List<Integer> B = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> answer = new ArrayList<>();
        for (int num = 100; num > 0; num--) {
            while (A.contains(num) && B.contains(num)) {
                answer.add(num);

                A = A.subList(A.indexOf(num) + 1, A.size());
                B = B.subList(B.indexOf(num) + 1, B.size());
            }
        }

        System.out.println(answer.size());
        for (int x : answer) {
            System.out.print(x + " ");
        }
    }
}
