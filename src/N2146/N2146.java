package N2146;

import java.util.*;
import java.io.*;

public class N2146 {

    static class Info {
        int r, c;

        public Info(int r, int c) {
            // TODO Auto-generated constructor stub
            this.r = r;
            this.c = c;
        }

    }

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dist;
    // 큐에 어떤 정보를 담을지?
    static Deque<Info> queue;
    // 최소 다리 수를 가질 변수(=정답)
    static int minBCnt;
    // 델타 배열
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    // 라벨링할 변수
    static int area;
    // 어떤 섬이 도달했는지 기록할 배열
    static int[][] islandId;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        dist = new int[N][N];
        queue = new ArrayDeque<>();

        // map 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        area = 0;
        // 섬 구분
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    queue.add(new Info(i, j));
                    visited[i][j] = true;
                    area++;
                    map[i][j] = area;
                    labelingBfs();

                }
            }
        }

        // 한 섬에서 다른 섬까지 거리 탐색
        area = 1;
        minBCnt= Integer.MAX_VALUE;
        queue = new ArrayDeque<>();
        visited = new boolean[N][N];
        islandId = new int[N][N];
        // 복사
        for(int i=0; i<N; i++) {
            islandId[i] = Arrays.copyOf(map[i], N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 칸에 전부 넣고 시작하기
                if (map[i][j] >= 1) {
                    queue.add(new Info(i, j));
                    visited[i][j] = true;
                    dist[i][j] = 0;
                }
            }
        }
        searchBridgeBfs();

        bw.write(minBCnt+"");
        bw.flush();


    } // main

    private static void searchBridgeBfs() {
        // TODO Auto-generated method stub
        while (!queue.isEmpty()) {
            Info curr = queue.remove();
            int r = curr.r;
            int c = curr.c;

            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                // 방문한 바다이거나 섬일 경우
                if (visited[nr][nc] || map[nr][nc] >= 1) {

                    // 어떤 섬에서 탐색한 건지 구분
                    if (islandId[nr][nc] != 0 && islandId[nr][nc] != islandId[r][c]) {
                        // 총 다리 길이 계산
                        int bridgeLen = dist[r][c] + dist[nr][nc];
                        // 현재까지 최소와 비교
                        minBCnt = Math.min(minBCnt, bridgeLen);

                    }

                    continue;
                } else {
                    // 탐색
                    dist[nr][nc] = dist[r][c] + 1;
                    islandId[nr][nc] = islandId[r][c];
                    visited[nr][nc] = true;
                    queue.add(new Info(nr, nc));
                }

            }

        }

    }

    // 섬을 구분하기 위한 bfs
    private static void labelingBfs() {
        // TODO Auto-generated method stub
        while (!queue.isEmpty()) {
            Info curr = queue.remove();
            int r = curr.r;
            int c = curr.c;

            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] != 1) {
                    continue;
                }

                map[nr][nc] = area;
                queue.add(new Info(nr, nc));
                visited[nr][nc] = true;
            } // 4
        }
    } // lB

}

