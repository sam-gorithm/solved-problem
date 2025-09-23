package BOJ_13549;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class N13549_jeongun {
    static int N, K;
    static int[] arr;
    static int[] dist;
    static boolean[] visited;
    static int max = 100000;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        dist = new int[max+1];
        visited = new boolean[max+1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        result = bfs(N,K);
        System.out.println(result);

    }
    static int bfs(int i, int j) {
        Queue<Integer> q = new ArrayDeque<>();
        dist[i] = 0;
        q.offer(i);

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;

            if (curr == j) {
                return dist[curr];
            }

            //순간이동할 때
            int nCurr = curr*2;
            if(nCurr <= max) {
                if(dist[nCurr] > dist[curr]) {
                    dist[nCurr] = dist[curr];
                    q.offer(nCurr);
                }
            }

            //curr-1
            nCurr = curr-1;
            if(nCurr >= 0) {
                if(dist[nCurr] > dist[curr]+1) {
                    dist[nCurr] = dist[curr]+1;
                    q.offer(nCurr);
                }
            }
            //curr+1
            nCurr = curr+1;
            if(nCurr <= max) {
                if(dist[nCurr] > dist[curr]+1) {
                    dist[nCurr] = dist[curr]+1;
                    q.offer(nCurr);
                }
            }
        }
        return -1;
    }
}
