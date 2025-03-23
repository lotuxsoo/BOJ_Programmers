import java.io.*;
import java.util.*;

public class Main {
    static long[] factorial;
    static boolean[] used;
    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        // 팩토리얼 미리 계산
        factorial = new long[21];
        factorial[0] = 1;
        for(int i = 1; i <= 20; i++) {
            factorial[i] = factorial[i-1] * i;
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int problem = Integer.parseInt(st.nextToken());
        used = new boolean[21];
        
        if(problem == 1) {
            long k = Long.parseLong(st.nextToken());
            findKthPermutation(k);
        } else {
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            findPermutationOrder(arr);
        }
    }
    
    // k번째 순열 찾기
    static void findKthPermutation(long k) {
        StringBuilder sb = new StringBuilder();
        k--; // 0-based로 변환
        
        for(int i = 0; i < n; i++) {
            for(int j = 1; j <= n; j++) {
                if(used[j]) continue;
                
                if(k >= factorial[n-1-i]) {
                    k -= factorial[n-1-i];
                } else {
                    used[j] = true;
                    sb.append(j).append(" ");
                    break;
                }
            }
        }
        System.out.println(sb);
    }
    
    // 순열의 순서 찾기
    static void findPermutationOrder(int[] arr) {
        long order = 1;
        
        for(int i = 0; i < n; i++) {
            int smaller = 0;
            for(int j = 1; j < arr[i]; j++) {
                if(!used[j]) smaller++;
            }
            order += smaller * factorial[n-1-i];
            used[arr[i]] = true;
        }
        
        System.out.println(order);
    }
}