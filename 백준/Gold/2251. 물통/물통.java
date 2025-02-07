import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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

    static int A, B, C;
    static List<Integer> ansList = new ArrayList<>();
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, C));

        visited = new boolean[A + 1][B + 1][C + 1];
        visited[0][0][C] = true; // 첫 상태 체크

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            // 큐에서 꺼낼때마다 A 비었는지 확인
            if (cur.a == 0) {
                ansList.add(cur.c);
            }

            // 물붓기 연산 (현상태에서 전부 수행)
            int move = Math.min(cur.a, B - cur.b); // a->b
            int newA = cur.a - move;
            int newB = cur.b + move;
            int newC = cur.c;

            if (!visited[newA][newB][newC]) {
                visited[newA][newB][newC] = true;
                queue.add(new State(newA, newB, newC));
            }

            move = Math.min(cur.a, C - cur.c); // a->c
            newA = cur.a - move;
            newB = cur.b;
            newC = cur.c + move;

            if (!visited[newA][newB][newC]) {
                visited[newA][newB][newC] = true;
                queue.add(new State(newA, newB, newC));
            }

            move = Math.min(cur.b, A - cur.a); // b->a
            newA = cur.a + move;
            newB = cur.b - move;
            newC = cur.c;

            if (!visited[newA][newB][newC]) {
                visited[newA][newB][newC] = true;
                queue.add(new State(newA, newB, newC));
            }

            move = Math.min(cur.b, C - cur.c); // b->c
            newA = cur.a;
            newB = cur.b - move;
            newC = cur.c + move;

            if (!visited[newA][newB][newC]) {
                visited[newA][newB][newC] = true;
                queue.add(new State(newA, newB, newC));
            }

            move = Math.min(cur.c, A - cur.a); // c->a
            newA = cur.a + move;
            newB = cur.b;
            newC = cur.c - move;

            if (!visited[newA][newB][newC]) {
                visited[newA][newB][newC] = true;
                queue.add(new State(newA, newB, newC));
            }

            move = Math.min(cur.c, B - cur.b); // c->b
            newA = cur.a;
            newB = cur.b + move;
            newC = cur.c - move;

            if (!visited[newA][newB][newC]) {
                visited[newA][newB][newC] = true;
                queue.add(new State(newA, newB, newC));
            }
        }

        Collections.sort(ansList);

        StringBuilder sb = new StringBuilder();
        for (int x : ansList) {
            sb.append(x + " ");
        }
        System.out.println(sb.toString());
    }
}
