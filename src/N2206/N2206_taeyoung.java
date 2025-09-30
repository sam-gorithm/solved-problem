package N2206;

import java.io.*;
import java.util.*;

public class N2206_taeyoung {
    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] A = new int[N][M];

        for (int i = 0; i < N; i++) {
            String S = br.readLine();
            for (int j = 0; j < M; j++) {
                A[i][j] = S.charAt(j) - '0';
            }
        }

        // 방문 체크 -> 3차원 배열 -> 0: 벽 안부수고 방문 / 1 : 벽 부수고 방문
        boolean[][][] checked = new boolean[2][N][M];

        Deque<int[]> Q = new ArrayDeque<>();

        Q.offer(new int[] { 0, 0, 1, 0 }); // 행, 열, 거리, 벽 부쉈는지
        checked[0][0][0] = true; // 방문체크

        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            int r = cur[0]; // 행
            int c = cur[1]; // 열
            int d = cur[2]; // 거리
            int b = cur[3]; // 벽 부쉈는지

            if (r == N - 1 && c == M - 1) { // 목표 도달
                System.out.println(d);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || checked[b][nr][nc])
                    continue;

                if (A[nr][nc] == 1) { // 벽인 경우
                    if (b == 0 && !checked[1][nr][nc]) { // 아직 안 부쉈고, 부순 뒤에 방문한 적 없으면
                        Q.offer(new int[] {nr, nc, d + 1, 1}); // 벽 부수고 이동
                        checked[1][nr][nc] = true; // 방문체크
                    }
                }
                else { // 그냥 이동 가능
                    Q.offer(new int[] {nr, nc, d + 1, b}); // 벽 상태 그대로 이동
                    checked[b][nr][nc] = true; // 방문체크
                }
            }
        }
        System.out.println(-1);
    }
}
