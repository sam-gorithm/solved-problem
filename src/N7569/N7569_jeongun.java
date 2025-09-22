package 토마토;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class N7569_jeongun {
    static int N, M, H;
    static int[][][] arr;
    static int dr[] = { 1, -1, 0, 0, 0, 0 };
    static int dc[] = { 0, 0, 1, -1, 0, 0 };
    static int dh[] = { 0, 0, 0, 0, 1, -1 }; // 위 아래 체크용

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt(); // 높이

        arr = new int[H][N][M];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    arr[k][i][j] = sc.nextInt();
                }
            }
        }

        int result = bfs();

        System.out.println(result);

    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();

        // 토마토 찾아서 큐에 넣기
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[k][i][j] == 1) {
                        q.offer(new int[] { i, j, k });
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int h = curr[2];

            for (int k = 0; k < 6; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                int nh = h + dh[k];

                if (nr < 0 || nc < 0 || nh < 0 || nr >= N || nc >= M || nh >= H || arr[nh][nr][nc] != 0) {
                    continue;
                }
                arr[nh][nr][nc] = arr[h][r][c] + 1;
                q.offer(new int[] { nr, nc, nh });
            }
        }
        int day = 0;
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[k][i][j] == 0) {
                        return -1;
                    }
                    day = Math.max(day, arr[k][i][j]);
                }
            }
        }

        return day - 1;
    }
}
