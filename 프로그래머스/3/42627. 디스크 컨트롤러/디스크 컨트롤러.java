import java.util.*;

class Solution {
    static class Task {
        int duration, requestTime, number;
        Task(int duration, int requestTime, int number) {
            this.duration = duration;
            this.requestTime = requestTime;
            this.number = number;
        }
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        ArrayList<Task> tasks = new ArrayList<>();
    
        int n = 0;
        for (int[] job : jobs) {
            tasks.add(new Task(job[1], job[0], n++));
        }
        
        Collections.sort(tasks, (t1, t2) -> {
            if (t1.duration == t2.duration) {
                if (t1.requestTime == t2.requestTime) {
                    return Integer.compare(t1.number, t2.number);
                }
                return Integer.compare(t1.requestTime, t2.requestTime);
            }
            return Integer.compare(t1.duration, t2.duration);
        });
        
        int[][] time = new int[n][2];
        
        int now = 0;
        
        boolean[] visited = new boolean[n];
        
        int cnt = n;
        
        while (cnt > 0) {
            boolean found = false;
            for (Task task : tasks) {
                if ((now >= task.requestTime) && !visited[task.number]) {
                    now += task.duration;
                    time[task.number] = new int[]{task.requestTime,now};
                    visited[task.number] = true;
                    found = true;
                    break;
                }
            }
            if (!found) now++;  
            else cnt--;
        }
        
        for (int[] a : time) {
            answer += a[1] - a[0];
        }
        
        answer /= n;
        
        return answer;
    }
}