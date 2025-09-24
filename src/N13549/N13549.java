package N13549;

import java.io.*;
import java.util.*;

public class N13549 {

    static int[] road;
    static int N, K;
    static boolean[] visited;
    static int minT;
    static Deque<Integer> queue;
    // 이동 배열
    static int[] dr = { -1, 1 };
    static final int L = 100000;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        road = new int[L + 1];
        visited = new boolean[L + 1];
        queue = new ArrayDeque<>();

        Arrays.fill(road, -1);
        // 0초 대에 할 일 초기값
        road[N] = 0;
//		System.out.println(Arrays.toString(road));
        visited[N] = true;
//		System.out.println(Arrays.toString(visited));
        queue.add(N);

        bfs();
        bw.write(road[K] + "");
        bw.flush();

    }

    private static void bfs() {
        // TODO Auto-generated method stub
        while (!queue.isEmpty()) {
            int curr = queue.remove();

            if (curr == K) {
                return;
            }
            // 0초 순간이동 기록
            int teleportationN = curr * 2;
            if (teleportationN >= 0 && teleportationN <= L && !visited[teleportationN]) {
                road[teleportationN] = road[curr];
                queue.addFirst(teleportationN);
                visited[teleportationN] = true;

            }

            // 1초 뒤 이전 이후 탐색
            for (int i = 0; i < dr.length; i++) {
                int nextN = curr + dr[i];

                if (nextN < 0 || nextN >= L + 1 || visited[nextN]) {
                    continue;
                }

                road[nextN] = road[curr] + 1;
                queue.add(nextN);
                visited[nextN] = true;

            } // 1

        } // while

    } // bfs
}

