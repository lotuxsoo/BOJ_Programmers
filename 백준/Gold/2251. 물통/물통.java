
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class State {
        int a, b, c;

        State(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static boolean[][][] visited;
    static ArrayList<Integer> ansList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        visited = new boolean[A + 1][B + 1][C + 1];

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, C));
        // visited[0][0][C] = true; 이거 때문에 오답

        while (!queue.isEmpty()) {
            State now = queue.poll();

            // 이미 검사한 상태인지 확인
            if (visited[now.a][now.b][now.c]) {
                continue;
            }
            visited[now.a][now.b][now.c] = true;

            // 정답 추가
            if (now.a == 0) {
                ansList.add(now.c);
            }

            // 6가지 상태 전이
            // a->b
            if (now.a + now.b <= B) {
                queue.add(new State(0, now.a + now.b, now.c));
            } else {
                queue.add(new State(now.a - (B - now.b), B, now.c));
            }

            // a->c
            if (now.a + now.c <= C) {
                queue.add(new State(0, now.b, now.a + now.c));
            } else {
                queue.add(new State(now.a - (C - now.c), now.b, C));
            }

            // b->a
            if (now.b + now.a <= A) {
                queue.add(new State(now.b + now.a, 0, now.c));
            } else {
                queue.add(new State(A, now.b - (A - now.a), now.c));
            }

            // b->c
            if (now.b + now.c <= C) {
                queue.add(new State(now.a, 0, now.b + now.c));
            } else {
                queue.add(new State(now.a, now.b - (C - now.c), C));
            }

            // c->a
            if (now.c + now.a <= A) {
                queue.add(new State(now.c + now.a, now.b, 0));
            } else {
                queue.add(new State(A, now.b, now.c - (A - now.a)));
            }

            // c->b
            if (now.c + now.b <= B) {
                queue.add(new State(now.a, now.c + now.b, 0));
            } else {
                queue.add(new State(now.a, B, now.c - (B - now.b)));
            }
        }

        Collections.sort(ansList);

        for (int x : ansList) {
            System.out.print(x + " ");
        }
    }
}
