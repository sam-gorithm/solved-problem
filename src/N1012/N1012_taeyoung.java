package N1012;

import java.util.*;
import java.io.*;

public class N1012_taeyoung {
    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] A = new int[M][N]; // 배추밭 배열 선언

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                A[X][Y] = 1; // 배추 위치 표기
            }

            boolean[][] checked = new boolean[M][N]; // 방문 체크 배열

            Queue<int[]> Q = new ArrayDeque<>(); // BFS를 위한 큐

            int ans = 0;

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (A[i][j] == 0 || checked[i][j]) // 배추가 없거나 이미 확인한 위치라면
                        continue; // 넘어간다.
                    Q.offer(new int[] { i, j }); // 배추 위치 큐에 넣기
                    checked[i][j] = true; // 방문 체크
                    while (!Q.isEmpty()) {
                        int[] cur = Q.poll();
                        for (int d = 0; d < 4; d++) { // 배추 위치에서 4방향 확인
                            int nr = cur[0] + dir[d][0];
                            int nc = cur[1] + dir[d][1];
                            // 인덱스 벗어나거나 이미 확인한 위치라면 넘어간다
                            if (nr < 0 || nr >= M || nc < 0 || nc >= N || checked[nr][nc])
                                continue;
                            if (A[nr][nc] == 1) { // 인접한 배추 존재
                                Q.offer(new int[] { nr, nc }); // 배추 위치 큐에 넣기
                                checked[nr][nc] = true; // 방문 체크
                            }
                        }
                    }
                    // 큐가 비었다 -> (i,j)에서 인접한 배추 모두 확인 완료 -> 지렁이 한마리 필요
                    ans++; // 1 증가
                }
            }
            System.out.println(ans);
        }
    }
}
