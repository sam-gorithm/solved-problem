package N5427;

import java.io.*;
import java.util.*;

// 불따로 상근 따로 근데 상근 bfs에서 불 위치 고려하기
public class N5427_seoyeon {
    static int w, h;
    static char[][] building;
    // 초 배열
    static int[][] fireSec;
    static int[][] escapeSec;
    // 큐
    static Deque<int[]> fireQueue;
    static Deque<int[]> escapeQueue;
    // 델타 배열 선언
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    // 탈출 확인 변수
    static boolean isEscape;
    // 탈출 시간 변수
    static int escapeAns;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            // 건물 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            isEscape=false;

            // 배열 입력받기
            building = new char[h][w];
            // 탐색 최단 시간을 저장할 배열
            fireSec = new int[h][w];
            escapeSec = new int[h][w];

            // 건물 입력받기
            for (int i = 0; i < h; i++) {
                building[i] = (br.readLine()).toCharArray();
            }

            fireQueue = new ArrayDeque<>();
            escapeQueue = new ArrayDeque<>();
            // 시작점 탐색
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    // 불의 위치는 전부 큐에 추가
                    if (building[i][j] == '*') {
                        fireQueue.add(new int[] { i, j });
                        fireSec[i][j] = 1;
                    }
                    // 상근의 시작점 큐에 추가
                    if (building[i][j] == '@') {
                        escapeQueue.add(new int[] { i, j });
                        escapeSec[i][j] = 1;
                    }
                }
            }
            // 불 경로 탐색
            fireBfs();
            // 상근 경로 탐색
            escapeBfs();

            // 탈출할 수 없는 경우
            if(!isEscape) {
                bw.write("IMPOSSIBLE");
                bw.newLine();
                bw.flush();
            }
            else {
                bw.write(Integer.toString(escapeAns));
                bw.newLine();
                bw.flush();
            }
        } // tc
    }

    private static void escapeBfs() {
        // TODO Auto-generated method stub
        while (!escapeQueue.isEmpty()) {
            int[] curr = escapeQueue.remove();
            int r = curr[0];
            int c = curr[1];

            // 4방향 탐색
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위확인 -> 탈출(배열범위를 넘어가면 탈출에 성공한 것)
                if (nr < 0 || nr >= h || nc < 0 || nc >= w) {
                    isEscape = true;
                    escapeAns = escapeSec[r][c];
                    return;
                }

                // 벽인지 확인 or 방문했는지 확인
                if (building[nr][nc] == '#' || escapeSec[nr][nc] > 0) {
                    continue;
                }

                // 불이 이미 붙었거나 다음 초에 확산될 예정인 경우
                // 0초라는 의미는 불이 붙지 않은 안전한 곳임.
                if (fireSec[nr][nc] != 0 && fireSec[nr][nc] <=  escapeSec[r][c] + 1 ) {
                    continue;
                }

                // 이외에는 탐색
                escapeSec[nr][nc] = escapeSec[r][c] + 1;
                escapeQueue.add(new int[] { nr, nc });

            } // 4
        } // while
    }

    // 불의 확산 탐색
    private static void fireBfs() {
        // TODO Auto-generated method stub
        while (!fireQueue.isEmpty()) {
            int[] curr = fireQueue.remove();
            int r = curr[0];
            int c = curr[1];

            // 4방향 탐색
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위확인
                if (nr < 0 || nr >= h || nc < 0 || nc >= w) {
                    continue;
                }

                // 벽인지 확인 or 방문했는지 확인
                if (building[nr][nc] == '#' || fireSec[nr][nc] > 0) {
                    continue;
                }

                // 이외에는 탐색
                fireSec[nr][nc] = fireSec[r][c] + 1;
                fireQueue.add(new int[] { nr, nc });

            } // 4
        } // while
    } // fireBfs

}

