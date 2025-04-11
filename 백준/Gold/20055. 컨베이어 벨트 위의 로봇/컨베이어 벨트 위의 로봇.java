
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Belt {
        int power;
        boolean robot;

        Belt(int power) {
            this.power = power;
            this.robot = false;
        }
    }

    static boolean finish() {
        int count = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (belts[i].power == 0) {
                count++;
            }
        }
        return count >= K;
    }

    static int N, K;
    static Belt[] belts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belts = new Belt[2 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belts[i] = new Belt(Integer.parseInt(st.nextToken()));
        }

        int step = 1;

        while (true) {
            // 1. 벨트가 로봇과 함께 한칸 회전한다.
            int p = belts[2 * N - 1].power;
            boolean r = belts[2 * N - 1].robot;
            for (int i = 2 * N - 1; i >= 1; i--) {
                belts[i].power = belts[i - 1].power;
                belts[i].robot = belts[i - 1].robot;
            }
            belts[0].power = p;
            belts[0].robot = r;

            // 내리는 위치 처리
            belts[N - 1].robot = false;

            // 2. 가장 먼저 올라간 로봇부터, 한칸 이동할 수 있으면 이동한다.
            for (int i = N - 2; i >= 0; i--) { // 역방향 처리
                if (!belts[i + 1].robot && belts[i].robot && belts[i + 1].power > 0) {
                    belts[i].robot = false;
                    belts[i + 1].robot = true;
                    belts[i + 1].power--;
                }
            }
            // 내리는 위치 처리
            belts[N - 1].robot = false;

            // 3. 1번 위치에 내구도>0이면 로봇을 올린다.
            if (belts[0].power > 0 && !belts[0].robot) {
                belts[0].robot = true;
                belts[0].power--;
            }

            // 4. 내구도==0인 칸의 개수가 K개 이상이면 종료한다.
            if (finish()) {
                break;
            }

            step++;
        }

        System.out.println(step);
    }
}