package 유기농배추;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class N1012_jeongun {
    static int T, N, M, K;
    static int[][] arr;
    static boolean[][] visited;
    static int dr[] = {1, 0, -1, 0};
    static int dc[] = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt(); //배추 좌표 개수

            arr = new int[N][M];
            visited = new boolean[N][M];

            //좌표 입력 받고, 안 받은 부분 다 0으로 처리
            //배추 심기
            for (int i = 0; i < K; i++) {
                int r = sc.nextInt();
                int c = sc.nextInt();
                arr[r][c] = 1;
                }

            int count = 0;

            //배추 영역 세기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 1 && !visited[i][j]) {
                        count++;
                        bfs(i, j);
                    }
                }
            }
            System.out.println(count);
        }
    }

    static void bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int curr[] = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }
                if (!visited[nr][nc] && arr[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
}