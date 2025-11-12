import java.util.*; 

public class Main {
	static int[][] map; 
	static List<int[]> snake = new ArrayList<>();
	static int n, k, l; 
	static HashMap<Integer, String> hash = new HashMap<>(); 
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 }; 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt(); // 보드의 크기 
		k = sc.nextInt(); // 사과의 개수 

		map = new int[n][n]; 
		for (int i = 0; i < k; i++) {
			int a = sc.nextInt() - 1; 
			int b = sc.nextInt() - 1; 
			map[a][b] = 1; 
		}

		l = sc.nextInt(); // 뱀의 방향 변환 횟수

		for (int i = 0; i < l; i++) {
			int x = sc.nextInt();   // x초
			String c = sc.next(); // 방향
			hash.put(x, c); 
		}
		solve();
	}

	public static void solve() {
		// 뱀의 현재 머리 위치
		int cx = 0, cy = 0; 
		int time = 0; 
		int d = 0; 
		
		snake.add(new int[] { 0, 0 });

		while (true) {
			
			time++;

			int nx = cx + dx[d]; 
			int ny = cy + dy[d]; 

			if (isFinish(nx, ny)) {
				break; 
			}

			if (map[nx][ny] == 1) {
				// 사과가 있을 때
				map[nx][ny] = 0; 
				snake.add(new int[] { nx, ny }); 
			} else {
				// 사과가 없을 때
				snake.add(new int[] { nx, ny }); 
				snake.remove(0);     
			}

			// 방향 전환 시간인지 확인
			if (hash.containsKey(time)) {
				// 방향 전환할 시간일 경우 
				if (hash.get(time).equals("D")) {
					// 오른쪽으로 90도 
					d += 1;
					if (d == 4) d = 0; 
				} else {
					// 왼쪽으로 90도
					d -= 1;
					if (d == -1) d = 3; 
				}
			}

			cx = nx;
			cy = ny;
		}

		System.out.println(time);
	}

	public static boolean isFinish(int nx, int ny) {
		// 벽에 부딪혔는지 확인
		if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
			return true;
		}

		// 자기 몸통에 부딪혔는지 확인
		for (int i = 0; i < snake.size(); i++) {
			int[] t = snake.get(i); 
			if (nx == t[0] && ny == t[1]) {
				return true;
			}
		}
		
		return false;
	}
}