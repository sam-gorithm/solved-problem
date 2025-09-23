package N2573;

import java.util.*;
import java.io.*;

public class N2573_taeyoung {
    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static int N, M;
    static int[][] A;
    static Deque<int[]> Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];

        Q = new ArrayDeque<>();

        // 배열 채우기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                // 빙산 -> 큐에 넣기
                if (A[i][j] != 0) {
                    Q.offer(new int[] { i, j });
                }
            }
        }

        int ans = 0; // 정답 -> 몇 년 지났는지

        while (!Q.isEmpty()) {
            ans++; // 1년 증가
            A = melt1year(); // 1년 뒤 빙산 배열

            if (isSeparated()) { // 분리되었다면
                System.out.println(ans); // 정답 출력
                return;
            }
        }
        // 큐가 비었다 -> 빙산 전부 녹을 때까지 분리되지 않음
        System.out.println(0);
    }

    // 1년 뒤의 빙산 배열을 반환하는 함수
    static int[][] melt1year() {
        int[][] next = new int[N][M]; // 1년 후 빙산 배열
        int l = Q.size();
        // 큐 크기만큼 반복문 -> 1년 단위로 bfs 진행
        for (int i = 0; i < l; i++) {
            int[] cur = Q.poll();
            int r = cur[0];
            int c = cur[1];
            next[r][c] = A[r][c]; // 빙산 -> 기존 배열 복사

            for (int j = 0; j < 4; j++) { // 4방향 확인
                int nr = r + dir[j][0];
                int nc = c + dir[j][1];

                // 인덱스 넘어가는 경우 넘어간다
                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;

                // 인접한 위치 바다 -> 1 녹음
                if (A[nr][nc] == 0) {
                    next[r][c]--;
                }
            }
            // 0보다 줄어들지 않으므로
            if (next[r][c] <= 0)
                next[r][c] = 0;
            // 0보다 큰건 아직 빙산 남아있음 -> 큐에 넣기
            else
                Q.offer(cur);
        }
        return next; // 1년 후 빙산 배열 반환
    }

    // 현재 빙산(A)이 분리되어 있는지 확인하는 함수
    // 구역 수 구하는 방법 활용 -> 구역이 2개 이상이면 분리
    static boolean isSeparated() {
        boolean[][] checked = new boolean[N][M]; // 방문 체크
        Deque<int[]> Q = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] != 0 && !checked[i][j]) {
                    cnt++;
                    if (cnt > 1) {
                        // 구역 2개 이상 -> 분리 -> true
                        return true;
                    }
                    Q.offer(new int[] { i, j });
                    checked[i][j] = true;
                    while (!Q.isEmpty()) {
                        int[] cur = Q.poll();
                        int r = cur[0];
                        int c = cur[1];
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dir[k][0];
                            int nc = c + dir[k][1];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= M || A[nr][nc] == 0 || checked[nr][nc])
                                continue;
                            Q.offer(new int[] { nr, nc });
                            checked[nr][nc] = true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
