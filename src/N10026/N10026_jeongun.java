package 적록색약;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class N10026_jeongun {
    static int N;
    static boolean[][] visited1;
    static boolean[][] visited2;
    static char[][] arr;
    static char[][] arrCopy;
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new char[N][N];
        visited1 = new boolean[N][N];

        int result1 = 0;
        int result2 = 0;

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        //정상인 구역 나누기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited1[i][j]) {
                    result1++;
                    bfs1(i, j);
                }
            }
        }

        arrCopy = new char[N][N];
        visited2 = new boolean[N][N];

        //적록색약용 배열
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arrCopy[i][j] = arr[i][j];
                if (arr[i][j] == 'R') {
                    arrCopy[i][j] = 'G';
                }
            }
        }
        //적록색약 구역 나누기

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited2[i][j]) {
                    result2++;
                    bfs2(i, j);
                }
            }
        }

        System.out.println(result1 + " " + result2);

    }

    static void bfs1(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        visited1[i][j] = true;

        char color = arr[i][j];

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }
                if (!visited1[nr][nc] && arr[nr][nc] == color) {
                    visited1[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }

    static void bfs2(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        visited2[i][j] = true;

        char color = arrCopy[i][j];

        while (!q.isEmpty()) {
            int curr[] = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nc < 0 || nr < 0 || nc >= N || nr >= N) {
                    continue;
                }
                if (!visited2[nr][nc] && arrCopy[nr][nc] == color) {
                    visited2[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
}