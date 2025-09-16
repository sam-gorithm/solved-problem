import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N1012_somin {
    static int M, N, K;
    static int[][] ground;
    static boolean[][] visited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            M = sc.nextInt(); // 가로 길이
            N = sc.nextInt(); // 세로 길이
            K = sc.nextInt(); // 배추 개수

            ground = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                int x = sc.nextInt(); // 열
                int y = sc.nextInt(); // 행
                ground[y][x] = 1;

            }

            int ans = 0; // 필요한 지렁이 수

            // 배추밭 순회
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 해당 위치에 배추가 있고 아직 방문하지 않았을 경우
                    if (ground[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j); // 연결된 배추 탐색
                        ans++;
                    }

                }

            }
            System.out.println(ans);
        }
    }

    public static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { i, j });
        // 방문처리
        visited[i][j] = true;

        // 탐색할 배추가 남아있으면 반복
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 네 방향 탐색
            for (int k = 0; k < 4; k++) {
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];

                // 범위를 벗어나지 않았고, 배추가 있으며, 아직 방문하지 않은 경우
                if (nr >= 0 && nc >= 0 && nr < N && nc < M && ground[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[] { nr, nc });
                }

            }
        }

    }

}