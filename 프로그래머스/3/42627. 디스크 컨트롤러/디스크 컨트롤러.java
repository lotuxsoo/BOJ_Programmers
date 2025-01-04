import java.util.*;

class Solution {
    static class Job implements Comparable<Job> {
        int index;
        int request;
        int duration;
        Job(int index,int request,int duration) {
            this.index = index;
            this.request = request;
            this.duration = duration;
        }
        @Override
        public int compareTo(Job o) {
            if (this.duration == o.duration) {
                if (this.request == o.request) {
                    return this.index - o.index;
                }
                return this.request - o.request;   
            }
            return this.duration - o.duration;
        }
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<Job> pq = new PriorityQueue<>();
        List<Job> list = new ArrayList<>();
        
        for (int i=0; i<jobs.length; i++) {
            list.add(new Job(i, jobs[i][0], jobs[i][1]));
        }
        
        Collections.sort(list, (o1,o2) -> o1.request - o2.request);
       
        int[] result = new int[jobs.length];
        int time = 0;
        int index = 0;
        
        while (index < list.size() || !pq.isEmpty()) {
            // 현재 시간에 요청된 작업을 우선순위 큐에 추가
            while (index < list.size() && list.get(index).request <= time) {
                pq.add(list.get(index));
                index++;
            }
            
            if (!pq.isEmpty()) {
                Job job = pq.poll();
                time += job.duration;
                result[job.index] = time - job.request;
            } else {
                time = list.get(index).request;
            }
        }
        
        
        
        int sum = 0;
        for (int x : result) {
            sum += x;
        }
        
        answer = sum / result.length;
        
        return answer;
    }
}