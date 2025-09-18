import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N10026_somin {

    static int N;
    static char[][] grid;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        grid = new char[N][N];
        for (int i = 0; i < N; i++) {
            // char 배열로
            grid[i] = sc.next().toCharArray();
        }

        // 적록색약이 아닌 사람
        visited = new boolean[N][N]; // 방문 배열 초기화
        int normalCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 아직 방문하지 않은 구역일 경우
                if (!visited[i][j]) {
                    bfs(i, j);
                    normalCnt++;
                }
            }
        }

        // 적록색약인 사람
        // 모든 G를 R로 변경
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 'G') {
                    grid[i][j] = 'R';
                }
            }
        }

        visited = new boolean[N][N]; // 방문 배열 초기화
        int notNormalCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 아직 방문하지 않은 구역일 경우
                if (!visited[i][j]) {
                    bfs(i, j);
                    notNormalCnt++;
                }
            }
        }

        System.out.println(normalCnt + " " + notNormalCnt);
    }

    public static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        char startColor = grid[i][j]; // 현재 구역 기준 색상

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 네 방향 탐색
            for (int k = 0; k < 4; k++) {
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];

                // 그리드 범위를 벗어나지 않고 방문하지 않았으며 현재 찾고 있는 색과 같을 경우 
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && grid[nr][nc] == startColor) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}