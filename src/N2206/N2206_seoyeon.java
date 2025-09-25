package N2206;

import java.io.*;
import java.util.*;

public class N2206_seoyeon {

    static class Info {
        int r, c, mapInfo;

        public Info(int r, int c, int mapInfo) {
            // TODO Auto-generated constructor stub
            this.r = r;
            this.c = c;
            this.mapInfo = mapInfo;
        }
    }

    static int N, M;
    static char[][] map;
    static int[][] dist1;
    static int[][] dist2;
    static boolean[][] visitied1;
    static boolean[][] visitied2;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static Deque<Info> queue;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 전부 초기화
        map = new char[N][M];
        dist1 = new int[N][M];
        dist2 = new int[N][M];
        visitied1 = new boolean[N][M];
        visitied2 = new boolean[N][M];
        queue = new ArrayDeque<>();

        // 맵 입력받기
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }

        // 시작점
        queue.add(new Info(0, 0, 0));
        dist1[0][0] = 1;
        visitied1[0][0] = true;

        bfs();

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(dist1[i]));
//		}
//		System.out.println("-----------------------------------------------");
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(dist2[i]));
//		}

        // 정답 출력
        if (dist1[N - 1][M - 1] == 0 && dist2[N - 1][M - 1] == 0) {
            bw.write("-1");
            bw.flush();

        }
        // 0이 아닌 거 출력
        else if (dist1[N - 1][M - 1] == 0 || dist2[N - 1][M - 1] == 0) {
            bw.write(Math.max(dist1[N - 1][M - 1], dist2[N - 1][M - 1])+"");
            bw.flush();
        }
        // 둘 다 0이 아닐 떄 가장 최소 출력
        else {
            bw.write(Math.min(dist1[N - 1][M - 1], dist2[N - 1][M - 1])+"");
            bw.flush();
        }

    } // main

    private static void bfs() {
        // TODO Auto-generated method stub

        while (!queue.isEmpty()) {
            Info curr = queue.remove();
            int r = curr.r;
            int c = curr.c;
            int mapN = curr.mapInfo;

            // 4방향 탐색
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 배열 범위 초과하면, 방문한 곳이면 다음 검사
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                // 아직 dist1 배열에서 탐색 중이라면 벽을 만났을 때 단 한 번 벽을 부술 수 있으므로
                // 벽을 만나면 dist2 배열을 가리키는 1을 넣음
                if (mapN == 0) {

                    // 방문하지 않았지만
                    if (!visitied1[nr][nc]) {
                        // 벽을 만났을 떄
                        if (map[nr][nc] == '1') {
                            // 방문 처리
                            visitied2[nr][nc] = true;
                            dist2[nr][nc] = dist1[r][c] + 1;
                            queue.add(new Info(nr, nc, 1));

                        }
                        // 길 일때 그냥 방문
                        else {
                            dist1[nr][nc] = dist1[r][c] + 1;
                            visitied1[nr][nc] = true;
                            queue.add(new Info(nr, nc, 0));
                        }

                    }

                }

                else {
                    // 길만 갈 수 있음.
                    if (map[nr][nc] == '1' || visitied2[nr][nc]) {
                        continue;
                    }

                    dist2[nr][nc] = dist2[r][c] + 1;
                    visitied2[nr][nc] = true;
                    queue.add(new Info(nr, nc, 1));

                }

            } // 4

        } // while

    } // bfs

}
