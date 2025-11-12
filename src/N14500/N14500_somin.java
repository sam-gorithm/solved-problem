import java.util.Scanner;

public class Main {

	static int max = Integer.MIN_VALUE;
	static int[][] arr;
	static boolean[][] visit;
	static int n;
	static int m;

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n][m];
		visit = new boolean[n][m];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				visit[i][j] = true;
				solve(i,j,arr[i][j],1);
				visit[i][j] = false;
			}
		}

		System.out.println(max);
	}

	static void solve(int row, int col, int sum, int count) {
		if(count == 4) {
			max = Math.max(max, sum); 
			return; 
		}

		for(int i = 0; i < 4; i++) {
			int curRow = row + dx[i];
			int curCol = col + dy[i];

			// 범위를 벗어났을 경우 
			if(curRow < 0 || curRow >= n || curCol < 0 || curCol >= m) {
				continue;
			}

			// 아직 방문하지 않은 칸일 경우 
			if(!visit[curRow][curCol]) {

				// 'ㅗ' 모양 따로 처리해줌 
				if(count == 2) {
					visit[curRow][curCol] = true;
          // 중심점에서 다른 방향으로 
					solve(row, col, sum + arr[curRow][curCol], count + 1);
					visit[curRow][curCol] = false;
				}

				visit[curRow][curCol] = true;
				solve(curRow, curCol, sum + arr[curRow][curCol], count + 1);
				visit[curRow][curCol] = false; 
			}
		}
	}
}