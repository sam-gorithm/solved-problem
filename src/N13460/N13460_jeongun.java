import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class N13460_jeongun {
	static int N, M, result;
	static char[][] arr;
	static boolean[][][][] visited;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		for(int i = 0; i < N; i++) {
			String str = sc.next();
			for(int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		int Rr = 0;
		int Rc = 0;
		int Br = 0;
		int Bc = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(arr[i][j] == 'R') {
					Rr = i;
					Rc = j;
					arr[i][j] = '.';
				}
				if(arr[i][j] == 'B') {
					Br = i;
					Bc = j;
					arr[i][j] = '.';
				}
			}
		}
		System.out.println(bfs(Rr,Rc,Br,Bc));
	}

	static int bfs(int Rr, int Rc, int Br, int Bc) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[Rr][Rc][Br][Bc] = true;
		q.offer(new int[]{Rr,Rc,Br,Bc,0}); // 0 -> 기울인 횟수
		
		while(!q.isEmpty()) {
			int curr[] = q.poll();
			int rr = curr[0];
			int rc = curr[1];
			int br = curr[2];
			int bc = curr[3];
			int dist = curr[4];
			
			if(dist >= 10) {
				continue;
			}
			
			for(int k = 0; k < 4; k++) {
				int r_r = rr;
				int r_c = rc;
				int rMove = 0;
				boolean rHole = false;
				
				//빨 구 이동 -> 탈출하기 전까지
				while(true) {
					int nr = r_r + dr[k];
					int nc = r_c + dc[k];
					
					//nr nc -> 다음 좌표 / r_r r_c -> 현재 좌표
					//벽 만나면 break
					if(arr[nr][nc] == '#') {
						break;
					}
					//좌표 갱신 + 구슬 이동거리++
					r_r = nr;
					r_c = nc;
					rMove++;
					
					if(arr[r_r][r_c] == 'O') {
						rHole = true;
						break;
					}
				}
				//파 구 이동
				int b_r = br;
				int b_c = bc;
				int bMove = 0;
				boolean bHole = false;
				
				while(true) {
					int nr = b_r + dr[k];
					int nc = b_c + dc[k];
					
					if(arr[nr][nc] == '#') {
						break;
					}
					
					b_r = nr;
					b_c = nc;
					bMove++;
					
					if(arr[b_r][b_c] == 'O') {
						bHole = true;
						break;
					}
				}
				//구슬 탈출했을 경우
				//파 구
				if(bHole) {
					continue;
				}
				//빨 구
				if(rHole) {
					return dist+1;
				}
				//구슬 좌표 같을 경우 늦게 온 구슬 한 칸 뒤로 보내기
				//move값 큰 애 = 멀리서 온 애 = 뒤로 보내야함
				if(r_r == b_r && r_c == b_c) {
					if(rMove > bMove) {
						r_r -= dr[k];
						r_c -= dc[k];
					} else {
						b_r -= dr[k];
						b_c -= dc[k];
					}
				}
				if(!visited[r_r][r_c][b_r][b_c]) {
					visited[r_r][r_c][b_r][b_c] = true;
					q.offer(new int[]{r_r,r_c,b_r,b_c,dist+1});
				}
			}

		}
		return -1;
	}
}
