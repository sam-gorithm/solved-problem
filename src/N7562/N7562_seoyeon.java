package N7562;

import java.io.*;
import java.util.*;

public class N7562_seoyeon {
    static int l;
    // 시작 좌표
    static int[] start;
    // 종료 좌표
    static int[] end;
    // 체스판
    static int[][] board;
    // 나이트의 이동경로를 담은 델타배열
    static int[] dr = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int[] dc = { 2, 1, -1, -2, 2, 1, -1, -2 };
    // 큐
    static Deque<int[]> queue;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {

            l = Integer.parseInt(br.readLine());
            board = new int[l][l];

            StringTokenizer st = new StringTokenizer(br.readLine());
            start = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
            st = new StringTokenizer(br.readLine());
            end = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
            // 입력 종료

            // 시작점 1로 초기화
            board[start[0]][start[1]] = 1;
            queue = new ArrayDeque<>();
            queue.add(start);

            bfs();

            bw.write((board[end[0]][end[1]] - 1) + "");
            bw.newLine();
            bw.flush();

        } // tc

    } // main

    private static void bfs() {
        // TODO Auto-generated method stub
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int r = curr[0];
            int c = curr[1];

            // 8방향탐색
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 배열 범위 초과 탐색
                if (nr < 0 || nr >= l || nc < 0 || nc >= l) {
                    continue;
                }

                // 이미 방문한 곳이지 확인
                if (board[nr][nc] >= 1) {
                    continue;
                }

                // 그 외에는 모두 탐색
                board[nr][nc] = board[r][c] + 1;
                queue.add(new int[] { nr, nc });
            } // 8
        }
    }

}
