package N2146;

import java.util.*;
import java.io.*;

public class N2146_taeyoung {
    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] map = new int[N][N]; // 섬을 구별하기 위한 배열
        boolean[][] checked = new boolean[N][N]; // 방문체크

        Deque<int[]> S = new ArrayDeque<>(); // 모든 지점 탐색 -> DFS 사용(스택)
        int x = 1; // 1번 섬부터 시작

        // map 배열에 섬 번호 입력하는 DFS
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] == 1 && !checked[i][j]) { // 육지, 방문 아직 안함
                    S.push(new int[] { i, j });
                    checked[i][j] = true;
                    // DFS
                    while (!S.isEmpty()) {
                        int[] cur = S.pop();
                        int r = cur[0];
                        int c = cur[1];
                        map[r][c] = x; // 섬 번호 입력

                        for (int k = 0; k < 4; k++) { // 4방향 탐색
                            int nr = r + dir[k][0];
                            int nc = c + dir[k][1];
                            // 인덱스, 방문, 육지 체크
                            if (nr < 0 || nr >= N || nc < 0 || nc >= N || checked[nr][nc] || A[nr][nc] == 0)
                                continue;
                            S.push(new int[] { nr, nc }); // 스택에 넣기
                            checked[nr][nc] = true; // 방문 체크
                        }
                    }
                    x++; // 섬 번호 증가
                }
            }
        }

        int ans = Integer.MAX_VALUE; // 정답 변수 초기값 설정

        // 섬마다 BFS
        // 1번 섬부터 x번 섬까지
        next: for (int island = 1; island <= x; island++) {
            boolean[][] visited = new boolean[N][N];
            Deque<int[]> Q = new ArrayDeque<>();
            // 섬의 가장자리만 큐에 넣기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == island) {
                        for (int d = 0; d < 4; d++) {
                            int nr = i + dir[d][0];
                            int nc = j + dir[d][1];
                            // 인덱스 체크
                            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                                continue;
                            // 바다와 인접, 아직 방문 안함
                            if (map[nr][nc] == 0 && !visited[i][j]) {
                                Q.offer(new int[] { i, j, 0 }); // {행, 열, 거리} 형태로 큐에 넣기
                                visited[i][j] = true; // 방문 체크
                                break;
                            }
                        }
                    }
                }
            }
            // BFS
            while (!Q.isEmpty()) {
                int[] cur = Q.poll();
                int r = cur[0];
                int c = cur[1];
                int d = cur[2];

                for (int i = 0; i < 4; i++) {
                    int nr = r + dir[i][0];
                    int nc = c + dir[i][1];

                    // 인덱스 체크
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                        continue;

                    // 0아니고, 현재 섬번호도 아님 -> 다른 섬 도착
                    if (map[nr][nc] != island && map[nr][nc] != 0) {
                        ans = Math.min(ans, d); // 다리 길이 갱신
                        continue next; // 다음 섬 확인하기
                    }

                    // 바다, 아직 방문 안했다면
                    if (map[nr][nc] == 0 && !visited[nr][nc]) {
                        Q.offer(new int[] { nr, nc, d + 1 }); // 거리 1 증가시켜 큐에 넣기
                        visited[nr][nc] = true; // 방문 체크
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
