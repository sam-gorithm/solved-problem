import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class N1600_somin {
	static class State {
		int x, y, k, moves; // x좌표, y좌표, 말처럼 움직인 횟수, 총 이동 횟수
		
		public State(int x, int y, int k, int moves) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.moves = moves;
		}
	}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        int W = sc.nextInt(); // 가로 길이
        int H = sc.nextInt(); // 세로 길이 

        int[][] board = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        boolean[][][] visited = new boolean[K + 1][H][W];

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        int[] hx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] hy = {1, -1, 2, -2, 2, -2, 1, -1};

        Queue<State> q = new LinkedList<>();

        // 시작점 (0, 0)을 큐에 추가
        q.add(new State(0, 0, 0, 0));
        visited[0][0][0] = true;

        int result = -1;

        while (!q.isEmpty()) {
            State current = q.poll();

            // 도착점에 도달하면 종료
            if (current.x == H - 1 && current.y == W - 1) {
                result = current.moves;
                break;
            }

            // 인접한 4방향으로 이동
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 범위, 장애물, 방문 여부 체크
                if (nx >= 0 && nx < H && ny >= 0 && ny < W && board[nx][ny] == 0 && !visited[current.k][nx][ny]) {
                    visited[current.k][nx][ny] = true;
                    q.add(new State(nx, ny, current.k, current.moves + 1));
                }
            }

            // 말처럼 8방향으로 이동
            if (current.k < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = current.x + hx[i];
                    int ny = current.y + hy[i];

                    // 범위, 장애물, 방문 여부 체크
                    if (nx >= 0 && nx < H && ny >= 0 && ny < W && board[nx][ny] == 0 && !visited[current.k + 1][nx][ny]) {
                        visited[current.k + 1][nx][ny] = true;
                        q.add(new State(nx, ny, current.k + 1, current.moves + 1));
                    }
                }
            }
        }

        System.out.println(result);
    }
}