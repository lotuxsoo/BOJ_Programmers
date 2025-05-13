
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static boolean check(int[] select) {
        boolean[] checkMap = new boolean[N * N];

        Queue<Integer> queue = new LinkedList<>();
        for (int t : teachers) {
            queue.add(t);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / N, y = cur % N;

            for (int i = 0; i < 4; i++) {
                int nx = x, ny = y;
                boolean flag = false;

                while (true) {
                    nx += dx[i];
                    ny += dy[i];

                    // 범위 벗어남, 이미 왔다감
                    if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
                        flag = true;
                        break;
                    }

                    // 장애물 만남
                    for (int s : select) {
                        if (s == nx * N + ny) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        break;
                    }

                    checkMap[nx * N + ny] = true;
                }
            }
        }

        boolean flag = true;
        for (int s : students) {
            if (checkMap[s]) { // 감시 당한 학생 발견
                flag = false;
            }
        }

        return flag;
    }

    static void backtrack(int depth, int start, int[] select) {
        if (found) {
            return;
        }

        if (depth == 3) {
            if (check(select)) {
                found = true;
            }
            return;
        }

        for (int i = start; i < blanks.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                select[depth] = blanks.get(i);
                backtrack(depth + 1, i + 1, select);
                visited[i] = false;
            }
        }
    }

    static int N;
    static char[] map;
    static ArrayList<Integer> blanks = new ArrayList<>();
    static ArrayList<Integer> teachers = new ArrayList<>();
    static ArrayList<Integer> students = new ArrayList<>();
    static boolean[] visited;
    static boolean found = false;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N * N];

        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i * N + j] = sp[j].charAt(0);
                if (map[i * N + j] == 'X') {
                    blanks.add(i * N + j);
                } else if (map[i * N + j] == 'S') {
                    students.add(i * N + j);
                } else if (map[i * N + j] == 'T') {
                    teachers.add(i * N + j);
                }
            }
        }

        visited = new boolean[N * N];
        backtrack(0, 0, new int[3]);

        if (found) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
