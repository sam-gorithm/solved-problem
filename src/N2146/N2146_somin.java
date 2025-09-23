import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N2146_somin {
    static int N;
    static int[][] map;
    static int[][] dist;
    static boolean[][] visited; 
    static int[] dr = {-1, 1, 0, 0}; 
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 섬 구분 
        int islandId = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    labelDfs(i, j, islandId++);
                }
            }
        }

        // 최단 다리 길이 찾기 
        Queue<Point> queue = new LinkedList<>();
        dist = new int[N][N];

        // 모든 육지 칸을 큐에 넣고, 거리 배열 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    queue.offer(new Point(i, j));
                    dist[i][j] = 0; 
                } else {
                    dist[i][j] = -1;
                }
            }
        }

        int minLength = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                // 인접한 칸이 다른 섬인 경우 -> 다리 완성
                if (map[nr][nc] > 0 && map[nr][nc] != map[cur.r][cur.c]) {
                    minLength = Math.min(minLength, dist[cur.r][cur.c] + dist[nr][nc]);
                }
                
                // 인접한 칸이 바다이고 아직 방문하지 않은 경우
                if (map[nr][nc] == 0 && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[cur.r][cur.c] + 1; // 거리 1 증가
                    map[nr][nc] = map[cur.r][cur.c]; // 바다에 현재 섬 번호 전파
                    queue.offer(new Point(nr, nc));
                }
            }
        }

        System.out.println(minLength);
    }

    // DFS로 연결된 모든 육지에 동일한 섬 번호 부여 
    public static void labelDfs(int r, int c, int islandId) {
        visited[r][c] = true;
        map[r][c] = islandId;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if (map[nr][nc] == 1 && !visited[nr][nc]) {
                    labelDfs(nr, nc, islandId);
                }
            }
        }
    }
}