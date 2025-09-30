package N1600;

import java.util.*;
import java.io.*;

public class N1600_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] A = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 일반 이동
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        // 말 이동
        int[][] dirH = { { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 }, { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 } };

        // 방문체크 -> 3차원 배열 활용 -> K
        boolean[][][] checked = new boolean[K + 1][H][W];
        Deque<int[]> Q = new ArrayDeque<>();
        Q.offer(new int[] { 0, 0, 0, 0 });
        checked[0][0][0] = true;


        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            int r = cur[0]; // 행
            int c = cur[1]; // 열
            int d = cur[2]; // 동작 횟수
            int k = cur[3]; // 말 이동 횟수

            if (r == H - 1 && c == W - 1) { // 목표 도달
                System.out.println(d);
                return;
            }

            if (k < K) { // 아직 말 이동 횟수 남음
                for (int i = 0; i < 8; i++) { // 말은 8방향 이동
                    int nr = r + dirH[i][0];
                    int nc = c + dirH[i][1];

                    // 인덱스 확인, 방문 여부 확인, 장애물 여부 확인
                    if (nr < 0 || nr >= H || nc < 0 || nc >= W || checked[k + 1][nr][nc] || A[nr][nc] == 1)
                        continue;

                    Q.offer(new int[] {nr, nc, d + 1, k + 1}); // 동작 + 1, 말 이동 + 1
                    checked[k + 1][nr][nc] = true;
                }
            }
            // 일반 이동
            for (int i = 0; i < 4; i++) {
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];

                // 인덱스 확인, 방문 여부 확인, 장애물 여부 확인
                if (nr < 0 || nr >= H || nc < 0 || nc >= W || checked[k][nr][nc] || A[nr][nc] == 1)
                    continue;

                Q.offer(new int[] {nr, nc, d + 1, k}); // 동작 + 1
                checked[k][nr][nc] = true;
            }
        }
        System.out.println(-1);
    }
}
