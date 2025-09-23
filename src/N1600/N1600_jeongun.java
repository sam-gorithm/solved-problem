package BOJ_1600;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int K, H, W;
    static int[][] arr;
    static int[][][] dist; //최소 거리 한 번에 + 말 점프 횟수 관리용 3차원
    static int[] dr1 = {0, 0, -1, 1}; //숭이
    static int[] dc1 = {1, -1, 0, 0};
    static int[] dr2 = {2, 2, 1, 1, -1, -1, -2, -2}; //말
    static int[] dc2 = {1, -1, 2, -2, 2, -2, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        K = sc.nextInt();
        W = sc.nextInt();
        H = sc.nextInt();

        arr = new int[H][W];
        dist = new int[H][W][K+1];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(bfs(0, 0));
    }

    static int bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j, K}); //r, c, 남은 말 점프 횟수
        dist[i][j][K] = 1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int k = curr[2];

            if (r == H-1 && c == W-1) {
                return dist[r][c][k]-1; //시작이 1이니까 -1 해줌
            }

            //숭이 이동
            for (int d = 0; d < 4; d++) {
                int nr = r + dr1[d];
                int nc = c + dc1[d];

                if (nr < 0 || nc < 0 || nr >= H || nc >= W) {
                    continue;
                }
                if (arr[nr][nc] == 1) {
                    continue;
                }
                if (dist[nr][nc][k] == 0) {
                    dist[nr][nc][k] = dist[r][c][k]+1;
                    q.offer(new int[]{nr,nc,k});
                }
            }

            //말 이동
            if (k > 0) {
                for (int d = 0; d < 8; d++) {
                    int nr = r + dr2[d];
                    int nc = c + dc2[d];

                    if (nr < 0 || nc < 0 || nr >= H || nc >= W) {
                        continue;
                    }
                    if (arr[nr][nc] == 1) {
                        continue;
                    }
                    if (dist[nr][nc][k-1] == 0) {
                        dist[nr][nc][k-1] = dist[r][c][k]+1;
                        q.offer(new int[]{nr, nc, k-1});
                    }
                }
            }
        }
        return -1;
    }
}
