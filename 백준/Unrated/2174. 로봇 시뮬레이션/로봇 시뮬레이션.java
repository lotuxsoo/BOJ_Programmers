
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static boolean solve(int index, char order, int repeat) {
        StringBuilder sb = new StringBuilder();
        int[] robot = robots.get(index);
        int dir = robot[2];

        if (order == 'L') { // 왼쪽 90도 회전
            for (int i = 0; i < repeat; i++) {
                dir = (dir + 3) % 4;
                robot[2] = dir;
            }

        } else if (order == 'R') { // 오른쪽 90도 회전
            for (int i = 0; i < repeat; i++) {
                dir = (dir + 1) % 4;
                robot[2] = dir;
            }
        } else if (order == 'F') { // 앞으로 한칸 이동
            for (int i = 0; i < repeat; i++) {
                int nx = robot[0] + dx[dir], ny = robot[1] + dy[dir];

                if (!(0 <= nx && nx < B && 0 <= ny && ny < A)) {
                    sb.append("Robot ").append(index + 1).append(" crashes into the wall");
                    System.out.println(sb.toString());
                    return false;
                }

                if (map[nx][ny] != -1) {
                    sb.append("Robot ").append(index + 1).append(" crashes into robot ").append(map[nx][ny] + 1);
                    System.out.println(sb.toString());
                    return false;
                }

                map[robot[0]][robot[1]] = -1; // 1. 기존 위치 지우기
                robot[0] = nx;
                robot[1] = ny; // 2. 위치 정보 업데이트
                map[nx][ny] = index; // 3. 앞으로 한칸 이동
            }
        }
        return true;
    }

    static int A, B, N, M;
    // N,E,S,W 방향
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<int[]> robots = new ArrayList<>();
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken()); // 가로
        B = Integer.parseInt(st.nextToken()); // 세로
        map = new int[B][A];
        for (int i = 0; i < B; i++) {
            Arrays.fill(map[i], -1);
        }

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 로봇 개수
        M = Integer.parseInt(st.nextToken()); // 명령 개수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1; // 가로
            int x = B - Integer.parseInt(st.nextToken()); // 세로
            char ch = st.nextToken().charAt(0);
            int c = 0;
            if (ch == 'N') {
                c = 0;
            } else if (ch == 'E') {
                c = 1;
            } else if (ch == 'S') {
                c = 2;
            } else if (ch == 'W') {
                c = 3;
            }
            robots.add(new int[]{x, y, c});
            map[x][y] = i;
        }

        boolean success = false;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken()) - 1; // 0-index
            char order = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());
            success = solve(index, order, repeat);
            if (!success) {
                return;
            }
        }
        if (success) {
            System.out.println("OK");
        }
    }
}
