import java.util.*;

class Solution {
    static class Song {
        int index;
        int number;
        Song(int index, int number) {
            this.index = index;
            this.number = number;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, List<Song>> map2 = new HashMap<>();
        
        for (int i=0; i<genres.length; i++) {
            map1.put(genres[i], map1.getOrDefault(genres[i],0)+plays[i]);
            map2.putIfAbsent(genres[i], new ArrayList<>());
            map2.get(genres[i]).add(new Song(i, plays[i]));
        }
        
        List<String> map1Keys = new ArrayList<>(map1.keySet());
        Collections.sort(map1Keys, (o1,o2) -> map1.get(o2) - map1.get(o1));
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for (String key : map1Keys) {
            List<Song> songs = map2.get(key);
            Collections.sort(songs, (o1,o2) -> o2.number - o1.number);
            for (int i=0; i<songs.size(); i++) {
                if (i == 2) break;
                result.add(songs.get(i).index);
            }
        }
        
        answer = new int[result.size()];
        for (int i=0; i<answer.length; i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}