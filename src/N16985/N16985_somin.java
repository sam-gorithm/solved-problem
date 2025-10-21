import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class N16985_somin {
	// board[회전방향][판번호][행][열]
	static int[][][][] board = new int[4][5][5][5];
	static int[][][] maze = new int[5][5][5];

	static int[] dz = { 1, -1, 0, 0, 0, 0 }; // 층
	static int[] dx = { 0, 0, 1, -1, 0, 0 }; // 행
	static int[] dy = { 0, 0, 0, 0, 1, -1 }; // 열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 5; i++) { // 판 번호
			for (int j = 0; j < 5; j++) { // 행
				for (int k = 0; k < 5; k++) { // 열
					board[0][i][j][k] = sc.nextInt();
				}
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int r = 1; r < 4; r++) {
				rotate(i, r);
			}
		}

		int[] order = { 0, 1, 2, 3, 4 }; // 판을 쌓는 순서
		int minDistance = Integer.MAX_VALUE;

		do {
			// 5개 판의 모든 회전 조합
			for (int tmp = 0; tmp < 1024; tmp++) {
				int brute = tmp;

				// 현재 순서와 회전에 맞는 미로 생성
				for (int i = 0; i < 5; i++) {
					int dir = brute % 4; // 0, 1, 2, 3 중 하나
					brute /= 4;
					int plateNum = order[i]; // 쌓을 판의 원래 번호

					for (int j = 0; j < 5; j++) {
						for (int k = 0; k < 5; k++) {
							maze[i][j][k] = board[dir][plateNum][j][k];
						}
					}
				}

				minDistance = Math.min(minDistance, bfs());
			}
		} while (nextPermutation(order)); // 다음 순열

		if (minDistance == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minDistance);
		}

	}

	public static int bfs() {
		// 입구나 출구가 막혀있으면 종료
		if (maze[0][0][0] == 0 || maze[4][4][4] == 0) {
			return Integer.MAX_VALUE;
		}

		int[][][] dist = new int[5][5][5];
		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { 0, 0, 0 }); // {z, x, y}
		dist[0][0][0] = 1;

		while (!q.isEmpty()) {
			int[] current = q.poll();
			int z = current[0];
			int x = current[1];
			int y = current[2];

			// 출구에 도달하기 바로 직전 칸일 경우
			if (z == 4 && x == 4 && y == 4) {
				return dist[z][x][y] - 1;
			}

			for (int i = 0; i < 6; i++) {
				int nz = z + dz[i];
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nz < 0 || nz >= 5 || nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
					continue;
				if (maze[nz][nx][ny] == 0 || dist[nz][nx][ny] != 0)
					continue;

				dist[nz][nx][ny] = dist[z][x][y] + 1;
				q.add(new int[] { nz, nx, ny });
			}
		}

		// 출구에 도달하지 못한 경우
		return Integer.MAX_VALUE;
	}

	public static void rotate(int plateNum, int r) {
		int[][] prevRotated = board[r - 1][plateNum];
		int[][] newRotated = board[r][plateNum];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				newRotated[i][j] = prevRotated[4 - j][i];
			}
		}
	}

	public static boolean nextPermutation(int[] arr) {
		int i = arr.length - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			i--;
		if (i <= 0)
			return false;

		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1])
			j--;

		swap(arr, i - 1, j);

		j = arr.length - 1;
		while (i < j) {
			swap(arr, i, j);
			i++;
			j--;
		}
		return true;
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}