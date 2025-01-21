import java.util.*;

class Solution {
    static Map<String, Integer> map = new HashMap<>();

    static void combination(int len, int N, int idx, char[] chars, StringBuilder sb) {
        if (len == N) {
            char[] ch = sb.toString().toCharArray();
            Arrays.sort(ch);
            String s = String.valueOf(ch);
            map.put(s, map.getOrDefault(s, 0) + 1);
            return;
        }

        for (int i = idx; i < chars.length; i++) {
            StringBuilder nsb = new StringBuilder(sb).append(chars[i]);
            combination(len + 1, N, i + 1, chars, nsb);
        }
    }

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        for (int i = 0; i < course.length; i++) {
            for (int j = 0; j < orders.length; j++) {
                combination(0, course[i], 0, orders[j].toCharArray(), new StringBuilder());
            }
        }

        ArrayList<String> keys = new ArrayList<>(map.keySet());
        keys.sort(Comparator.comparing(String::length)
                .thenComparing((o1, o2) -> Integer.compare(map.get(o2), map.get(o1))));

        ArrayList<String> answerList = new ArrayList<>();

        int len = keys.get(0).length();
        int num = map.get(keys.get(0));

        for (String key : keys) {
            if (key.length() == len) {
                if (map.get(key) == num && map.get(key) >= 2) {
                    answerList.add(key);
                }
            } else {
                if (map.get(key) >= 2) {
                    answerList.add(key);
                    num = map.get(key);
                }
                len = key.length();
            }
        }

        answerList.sort((o1, o2) -> o1.compareTo(o2));
        //answerList.sort(String::compareTo);

        answer = new String[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}