package N10026;

import java.io.*;
import java.util.*;

public class N10026_seoyeon {

    static int N; // 그림의 크기
    static char[][] paint; // 적록색약이 아닌 사람
    static boolean[][] visited; // 적록색약이 아닌 사람
    static char[][] paint2; // 적록색약인 사람의 그림
    static boolean[][] visited2; // 적록색약인 사람의 그림 구역 방문여부
    static int partCnt = 0; // 적록색약이 아닌 사람의 그림 영역
    static int partCnt2 = 0; // 적록색약인 사람의 그림 영역
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static Deque<int[]> queue;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        paint = new char[N][N];
        paint2 = new char[N][N];
        visited = new boolean[N][N];
        visited2 = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            paint[i] = line.toCharArray();

            // 적록 색약의 배열 복사하여 변경
            paint2[i] = Arrays.copyOf(paint[i], N);
            for (int j = 0; j < N; j++) {
                if (paint2[i][j] == 'R') {
                    paint2[i][j] = 'G';
                }
            } // R을 G로 치환
        } // 입력받기

        queue = new ArrayDeque<>();
        // 탐색 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    queue.add(new int[] { i, j });
                    visited[i][j] = true;
                    partCnt++;
                    bfs(true);

                }
            } // 적록색약 아님

            for (int k = 0; k < N; k++) {
                if (!visited2[i][k]) {
                    queue.add(new int[] { i, k });
                    visited2[i][k] = true;
                    partCnt2++;
                    bfs(false);

                }
            } // 적록색약의 탐색
        } // 행

        System.out.println(partCnt + " " + partCnt2);

    } // main

    private static void bfs(boolean isN) {
        // TODO Auto-generated method stub
        while (!queue.isEmpty()) {
            int curr[] = queue.remove();
            int r = curr[0];
            int c = curr[1];

            // 4방향 탐색
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                if (isN) {
                    // 방문했거나, 지금 전에 순회하고 있던 것과 글자가 다르면
                    if (visited[nr][nc] || paint[nr][nc] != paint[r][c]) {
                        continue;
                    }
                } else {
                    // 방문했거나, 지금 전에 순회하고 있던 것과 글자가 다르면
                    if (visited2[nr][nc] || paint2[nr][nc] != paint2[r][c]) {
                        continue;
                    }
                }

                // 적록색약이면 else
                if (isN) {
                    visited[nr][nc] = true;
                    queue.add(new int[] { nr, nc });
                } else {
                    visited2[nr][nc] = true;
                    queue.add(new int[] { nr, nc });
                }
            } // 4
        } // while
    }

}
