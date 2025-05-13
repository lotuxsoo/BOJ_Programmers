
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static void find(int x, int y, int dir, int[][] tempMap) {
        int nx = x;
        int ny = y;

        while ((0 <= nx && nx < N && 0 <= ny && ny < M) && tempMap[nx][ny] != 6) {
            if (tempMap[nx][ny] == 0) {
                tempMap[nx][ny] = -1;
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    static int simulate() {
        int[][] tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            tempMap[i] = map[i].clone();
        }

        for (Cctv cctv : cctvs) {
            int x = cctv.x;
            int y = cctv.y;
            int dir = cctv.dir;
            int type = cctv.type;

            switch (type) {
                case 1:
                    find(x, y, dir, tempMap);
                    break;
                case 2:
                    find(x, y, dir, tempMap);
                    find(x, y, (dir + 2) % 4, tempMap);
                    break;
                case 3:
                    find(x, y, dir, tempMap);
                    find(x, y, (dir + 1) % 4, tempMap);
                    break;
                case 4:
                    find(x, y, dir, tempMap);
                    find(x, y, (dir + 1) % 4, tempMap);
                    find(x, y, (dir + 2) % 4, tempMap);
                    break;
                case 5:
                    find(x, y, dir, tempMap);
                    find(x, y, (dir + 1) % 4, tempMap);
                    find(x, y, (dir + 2) % 4, tempMap);
                    find(x, y, (dir + 3) % 4, tempMap);
                    break;
            }
        }

        int blind = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] == 0) {
                    blind++;
                }
            }
        }
        return blind;
    }

    static void backtrack(int index) {
        if (index == cctvs.size()) {
            result = Math.min(result, simulate());
            return;
        }

        Cctv cctv = cctvs.get(index);
        int type = cctv.type;
        int rotation = 4;

        if (type == 5) {
            backtrack(index + 1);
            return;
        }
        if (type == 2) {
            rotation = 2;
        }

        for (int i = 0; i < rotation; i++) {
            cctv.dir = i; // 4방향 모두 지정, 조합 만들기
            backtrack(index + 1);
        }
    }

    static int result = Integer.MAX_VALUE;
    static int N, M;
    static int[][] map;
    static ArrayList<Cctv> cctvs = new ArrayList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            sp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(sp[j]);
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    // cctv 좌표, cctv 감시방향, cctv 종료
                    cctvs.add(new Cctv(i, j, 0, map[i][j]));
                }
            }
        }

        backtrack(0);

        System.out.println(result);
    }

    static class Cctv {
        int x, y, dir, type;

        Cctv(int x, int y, int dir, int type) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.type = type;
        }
    }
}
