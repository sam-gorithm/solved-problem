package BOJ_2146;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int[][] arr;
    static int[][] island;
    static int[][] dist;
    static boolean[][] visited1;
    static int dr[] = { 0, 0, 1, -1 };
    static int dc[] = { 1, -1, 0, 0 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        arr = new int[N][N];
        island = new int[N][N];
        visited1 = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1 && !visited1[i][j]) {
                    count++;
                    bfs1(i, j, count);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for(int i = 1; i <= count; i++) {

            int min = bfs2(i);
            if(min < result) {
                result = min;
            }
        }

        System.out.println(result);

    }

    static void bfs1(int i, int j, int num) { // 섬 덩어리 번호 지정해주기
        Queue<int[]> q = new ArrayDeque<>();
        visited1[i][j] = true;
        island[i][j] = num;
        q.offer(new int[] { i, j });

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited1[nr][nc] || arr[nr][nc] == 0) {
                    continue;
                }
                visited1[nr][nc] = true;
                island[nr][nc] = num;
                q.offer(new int[] { nr, nc });
            }
        }
    }

    static int bfs2(int num) { // 섬 잇는 다리 만들기
        Queue<int[]> q = new ArrayDeque<>();

        dist = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                dist[r][c] = -1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (island[i][j] == num) {
                    q.offer(new int[] { i, j });// 섬 지정 번호 큐에 넣기
                    dist[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }
                if (island[nr][nc] != 0 && island[nr][nc] != num) {
                    return dist[r][c];
                }

                if (island[nr][nc] == 0 && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c]+1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return 0;
    }
}
