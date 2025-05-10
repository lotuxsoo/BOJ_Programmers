
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static class Info {
        String name;
        int korean, english, math;

        Info(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }
    }

    static int N;
    static ArrayList<Info> infos = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");
            String name = sp[0];
            int korean = Integer.parseInt(sp[1]);
            int english = Integer.parseInt(sp[2]);
            int math = Integer.parseInt(sp[3]);
            infos.add(new Info(name, korean, english, math));
        }

        Collections.sort(infos, (a, b) -> {
            if (a.korean == b.korean) {
                if (a.english == b.english) {
                    if (a.math == b.math) {
                        return a.name.compareTo(b.name);
                    }
                    return Integer.compare(b.math, a.math); // 감소, 내림차순
                }
                return Integer.compare(a.english, b.english); // 증가, 오름차순
            }
            return Integer.compare(b.korean, a.korean); // 감소, 내림차순
        });

        for (Info info : infos) {
            System.out.println(info.name);
        }
    }
}
