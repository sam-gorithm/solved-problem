import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N7569_somin {
    static int m, n, h; // m: 가로, n: 세로, h: 높이
    static int res;
    static boolean chk = true;
    static int[][][] arr; // 토마토의 상태 저장
    static int[][][] visited;
    static Queue<int[]> q;

    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();
        h = sc.nextInt();

        arr = new int[h][n][m];
        visited = new int[h][n][m];
        q = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    arr[i][j][k] = sc.nextInt();

                    if (arr[i][j][k] == 1) { // 익은 토마토일 경우
                        q.add(new int[]{i, j, k}); // 큐에 추가
                        visited[i][j][k] = 1;     
                    } else if (arr[i][j][k] == 0) { // 익지 않은 토마토일 경우
                        chk = false; // 익지 않은 토마토 존재
                    }
                }
            }
        }

        if (chk) { // 처음부터 모든 토마토가 익어있었다면
            System.out.println(0);
        } else {
            bfs();
            System.out.println(check());
        }
    }

    public static void bfs() {
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int z = p[0];
            int x = p[1];
            int y = p[2];

            for (int d = 0; d < 6; d++) {
                int nz = z + dz[d];
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 상자 범위를 벗어났을 경우 건너뛰기
                if (nz < 0 || nx < 0 || ny < 0 || nz >= h || nx >= n || ny >= m) continue;

                // 다음 칸이 익지 않은 토마토이고 아직 방문하지 않았을 경우
                if (arr[nz][nx][ny] == 0 && visited[nz][nx][ny] == 0) {
                    q.add(new int[]{nz, nx, ny});
                    // 현재 칸의 날짜 + 1을 다음 칸에 기록
                    visited[nz][nx][ny] = visited[z][x][y] + 1;
                }
            }
        }
    }

    // 모든 토마토가 익었는지와 최대 소요 일수를 확인
    public static int check() {
        res = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {

                    if (arr[i][j][k] == 0 && visited[i][j][k] == 0) {
                        return -1; //
                    }
                    // 가장 오래 걸린 날짜
                    if (visited[i][j][k] > res) {
                        res = visited[i][j][k];
                    }
                }
            }
        }
        return res - 1;
    }
}