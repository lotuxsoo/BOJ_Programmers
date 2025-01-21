import java.util.*;

class Solution {
    static class Music {
        int index;
        int plays;
        
        Music(int index, int plays) {
            this.index = index;
            this.plays = plays;
        }
        
        public int getIndex() {
            return this.index;
        }
        
        public int getPlays() {
            return this.plays;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        Map<String,ArrayList<Music>> map = new HashMap<>();
        
        int n = genres.length;
        for (int i=0; i<n; i++) {
            map.putIfAbsent(genres[i], new ArrayList<>());
            map.get(genres[i]).add(new Music(i, plays[i]));
        }
        
        Map<String,Integer> totals = new HashMap<>();
        for (String key : map.keySet()) {
            int sum = 0;
            ArrayList<Music> list = map.get(key);
            for (Music m : list) {
                sum += m.plays;
            }
            totals.put(key, sum);
        }
        
        ArrayList<String> keyList = new ArrayList<>(totals.keySet());
        Collections.sort(keyList, Comparator.comparing(k -> totals.get(k)).reversed());
        
        ArrayList<Integer> ansList = new ArrayList<>();
        
        for (String key : keyList) {
            ArrayList<Music> list = map.get(key);
            Collections.sort(list, Comparator.comparing(Music::getPlays).reversed()
                            .thenComparing(Music::getIndex));
        
            int i = 0;
            while (i<2 && i<list.size()) {
                ansList.add(list.get(i).index);
                i++;
            }
        }
    
        answer = new int[ansList.size()];
        for (int i=0; i<answer.length; i++) {
            answer[i] = ansList.get(i);
        }
         
        return answer;
    }
}