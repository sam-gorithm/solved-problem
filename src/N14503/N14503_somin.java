import java.util.Scanner;

public class N14503_somin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        // 로봇의 초기 위치 (x, y)와 방향 d
        int x = sc.nextInt();
        int y = sc.nextInt();
        int d = sc.nextInt();

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        int dx[] = {-1, 0, 1, 0};
        int dy[] = {0, 1, 0, -1};

        int ans = 0; // 청소한 칸의 수

        while (true) {
            // 아직 청소되지 않은 경우 현재 칸을 청소
            if (board[x][y] == 0) {
                ans++;
                board[x][y] = -1; // 청소 완료 표시
            }

            boolean cleaned = false; // 주변 4칸 중 청소가 된 곳 

            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4; 

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    cleaned = true;
                    break;
                }
            }

            if (cleaned) {
                continue;
            }

            int bx = x - dx[d]; 
            int by = y - dy[d];

            if (bx < 0 || bx >= N || by < 0 || by >= M || board[bx][by] == 1) {
                break; 
            }

            x = bx;
            y = by;
        }

        System.out.println(ans);
    }
}