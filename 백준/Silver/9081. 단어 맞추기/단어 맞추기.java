
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static void nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        } // 뒤에서부터 오름차순 확인
        if (i == -1) {
            System.out.println(new String(arr));
            return;
        }

        if (i >= 0) {
            int j = arr.length - 1;
            while (arr[j] <= arr[i]) { // 오른쪽에 있는거중 나보다 제일 큰거 찾음
                j--;
            }

            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        int left = i + 1, right = arr.length - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

        System.out.println(new String(arr));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            char[] arr = br.readLine().toCharArray();
            nextPermutation(arr);
        }
    }
}
