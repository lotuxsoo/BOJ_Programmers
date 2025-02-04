import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean isValid(int[] tempArr) {
        boolean b = true;
        for (int k = 0; k < 26; k++) {
            if (tempArr[k] < dnaArr[k]) {
                return false;
            }
        }

        return b;
    }

    static int S, P; // 문자열 길이, 부분문자열 길이
    static String dnaString;
    static char[] dnaChar = {'A', 'C', 'G', 'T'};
    static int[] dnaArr = new int[26]; // 알파벳 26자

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        dnaString = br.readLine();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            dnaArr[dnaChar[i] - 'A'] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        int[] tempArr = new int[26];

        // **O(P)**
        for (int i = 0; i < P; i++) {
            char ch = dnaString.charAt(i);
            tempArr[ch - 'A']++;
        }

        // **O(1)**
        if (isValid(tempArr)) {
            answer++;
        }

        // **O(S-P)**
        int end = P;
        while (end < S) {
            char last = dnaString.charAt(end - P);
            tempArr[last - 'A']--;
            char next = dnaString.charAt(end);
            tempArr[next - 'A']++;

            // **O(1)**
            if (isValid(tempArr)) {
                answer++;
            }
            end++;
        }

        System.out.println(answer);
    }
}
