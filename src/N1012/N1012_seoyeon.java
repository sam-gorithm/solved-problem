package N1012;

import java.io.*;
import java.util.*;

// 같은 애벌레가 해충을 잡아먹을 수 있는 구역은 같은 수로 표현하기
public class N1012_seoyeon {

    static int M, N, K;
    static int[][] farm;
    static boolean[][] visited;
    static Deque<int[]> queue;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int maxWorm = 0;

            farm = new int[N][M];
            visited = new boolean[N][M];

            // 배추 심어진 곳 입력받기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                farm[y][x] = 1;
            }

            queue = new ArrayDeque<>();

            // 시작점 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (farm[i][j] == 1 && !visited[i][j]) {
                        // 시작 정점이 담아질 때 벌레가 각각의 구역마다 최소로 배치될 것임.
                        maxWorm++;
                        queue.add(new int[] { i, j });
                        visited[i][j] = true;
                        bfs();

                    }
                }
            }

            System.out.println(maxWorm);
        } // tc

    } // main

    private static void bfs() {

        // 큐가 비지 않을 때까지 반복 + 다음 1을 찾아야 함..
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int r = curr[0];
            int c = curr[1];

            // 4방향 탐색
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위 초과
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                // 배추밭이 아니거나 이미 방문한 밭일 경우
                if (farm[nr][nc] == 0 || visited[nr][nc]) {
                    continue;
                }

                // 그게 아니면 방문 가능
                visited[nr][nc] = true;
                queue.add(new int[] { nr, nc });

            } // 4

        } // while
    } // bfs

}
