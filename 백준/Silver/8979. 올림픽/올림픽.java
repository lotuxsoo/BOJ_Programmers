
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Info implements Comparable<Info> {
        int idx, gold, silver, bronze;

        Info(int idx, int gold, int silver, int bronze) {
            this.idx = idx;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Info o) {
            if (this.gold == o.gold) {
                if (this.silver == o.silver) {
                    return o.bronze - this.bronze;
                }
                return o.silver - this.silver;
            }
            return o.gold - this.gold;
        }
    }

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ArrayList<Info> infos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            infos.add(new Info(n, g, s, b));
        }

        Collections.sort(infos);

        int[] ranks = new int[N + 1]; // 국가 번호를 인덱스로 사용

        int rank = 1;
        int gold = infos.get(0).gold, silver = infos.get(0).silver, bronze = infos.get(0).bronze;
        ranks[infos.get(0).idx] = rank;
        for (int i = 1; i < N; i++) {
            int g = infos.get(i).gold, s = infos.get(i).silver, b = infos.get(i).bronze;
            if (gold == g && silver == s && bronze == b) {
                ranks[infos.get(i).idx] = rank;
            } else {
                gold = g;
                silver = s;
                bronze = b;
                rank++;
            }
        }

        System.out.println(ranks[K]);
    }
}
