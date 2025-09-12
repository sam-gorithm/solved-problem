package N10026;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//	적록색약 / 골드5 / 132ms
public class N10026_jinhyuk {
	//static
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int cnt1, cnt2;	//색약X, 색약O
	
	//4방향 탐색 (상하좌우)
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	//Pos
	static class Pos {
		int r;		//행
		int c;		//열
		char ch;	//색

		public Pos(int r, int c, char ch) {
			this.r = r;
			this.c = c;
			this.ch = ch;
		}
	}
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String str = sc.next();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
			}
		}//입력
		
		//색약X
		cnt1 = 0;
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					//탐색하지않았다면 bfs, 구역 추가
					bfs(i,j, map[i][j]);
					cnt1++;
				}
			}
		}
		
		//'G' -> 'R'로 변경
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 'G') {
					map[i][j] = 'R';
				}
			}
		}
		
		//색약O
		cnt2 = 0;
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					//탐색하지않았다면 bfs, 구역 추가
					bfs(i,j, map[i][j]);
					cnt2++;
				}
			}
		}

		//결과출력
		System.out.printf("%d %d", cnt1, cnt2);
	}	//main

	
	//bfs
	private static void bfs(int r, int c, char ch) {
		Queue<Pos> q = new LinkedList<>();
		Pos p = new Pos(r, c, ch);
		q.add(p);
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			for(int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				//범위체크
				if(nr >= 0 && nc >= 0 && nr < N && nc < N) {
					char nch = map[nr][nc];
					//방문하지않았고, 탐색시작지점색과 같다면
					if(!visited[nr][nc] && ch == nch) {
						Pos p2 = new Pos(nr, nc, nch);
						q.add(p2);
						visited[nr][nc] = true;
					}
				}
			}//4방향 델타
		}//while
		
		return;
	}	//bfs
	

}	//class
