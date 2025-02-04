import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, L; // 숫자 개수, 윈도우 크기
    static int[] A;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        answer = new int[N];

        // 모노토닉 덱
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {

            // 최솟값 인덱스 범위 확인
            if (!deque.isEmpty() && deque.peekFirst() < i - L + 1) {
                deque.pollFirst();
            }

            // 삽입하면서 최솟값 유지
            while (!deque.isEmpty() && A[deque.peekLast()] >= A[i]) {
                deque.pollLast();
            }
            deque.addLast(i);

            // 최솟값 저장
            answer[i] = A[deque.peekFirst()];
        }

        StringBuilder sb = new StringBuilder();
        for (int x : answer) {
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString());
    }
}
