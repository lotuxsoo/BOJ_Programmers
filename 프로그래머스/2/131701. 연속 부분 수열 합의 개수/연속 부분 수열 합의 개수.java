import java.io.*;
import java.util.*;

class Solution {
    static int length;
    static int[] arr;
    static int[] sum;
    static Set<Integer> sumSet = new HashSet<>();
    
    static int makeSum() {

        for (int len=1; len<=length; len++) { // 부분수열 길이
            for (int start=0; start<length; start++) { // 시작점
                int sum = 0;
                for (int i=0; i<len; i++) {
                    sum += arr[start+i];
                }
                sumSet.add(sum);
            }
        }
        return sumSet.size();
    }
    
    
    public int solution(int[] elements) {
        int answer = 0;
        length = elements.length;
        
        arr = new int[length*2];
        for (int i=0; i<length; i++) {
            arr[i] = elements[i];
            arr[i+length] = elements[i];
        }
        
        answer = makeSum();
      
        return answer;
    }
}