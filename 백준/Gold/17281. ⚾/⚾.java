
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int play(ArrayList<Integer> list) {
        int total = 0;
        int i = 0;

        // 이닝 종료 조건: out == 3
        for (int round = 0; round < N; round++) {
            // 0:1루 1:2루 2:3루
            int out = 0;
            int[] base = new int[3];

            while (out < 3) {
                i %= 9;
                int idx = list.get(i);
                int score = scores[round][idx];
                if (score == 1) {
                    total += base[2];
                    base[2] = base[1];
                    base[1] = base[0];
                    base[0] = 1;
                } else if (score == 2) {
                    total += base[2] + base[1];
                    base[2] = base[0];
                    base[1] = 1;
                    base[0] = 0;
                } else if (score == 3) {
                    total += base[2] + base[1] + base[0];
                    base[2] = 1;
                    base[1] = 0;
                    base[0] = 0;
                } else if (score == 4) {
                    total += base[2] + base[1] + base[0] + 1;
                    base[2] = 0;
                    base[1] = 0;
                    base[0] = 0;
                } else if (score == 0) {
                    out++;
                }

                i++; // 이닝이 끝날때도 타순 하나 증가
                if (out == 3) {
                    break;
                }
            }
        }

        return total;
    }

    static void perm(ArrayList<Integer> list) {
        if (list.size() == 9) {
            // 경기 계산
            maxScore = Math.max(maxScore, play(new ArrayList<>(list)));
            return;
        }

        if (list.size() == 3) {
            list.add(0); // 1번 선수는 4번 타자로 미리 결정
            perm(list);
            list.remove(list.size() - 1);
            return; // 실행 차단
        }

        for (int i = 1; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(i);
                perm(list);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    static int maxScore = 0;
    static int N;
    static int[][] scores;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        scores = new int[N][9];

        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                scores[i][j] = Integer.parseInt(sp[j]);
            }
        }

        visited = new boolean[9]; // 0~8
        visited[0] = true; // 0번 선수 미리 결정
        perm(new ArrayList<>());

        System.out.println(maxScore);
    }
}
