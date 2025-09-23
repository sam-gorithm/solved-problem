package N1600;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//	말이 되고픈 원숭이 / 골드 3 / 876ms
public class N1600_jinhyuk {
	//static
	static int K;					//말처럼 움직일 수 있는 횟수
	static int W, H;				//가로길이(열), 세로길이(행)
	static int[][] board;			//격자판
	static boolean[][][] visited;	//(행/열/말처럼이동한횟수)
	
	//말 움직임 (1시부터-시계방향)
	static int[] dr1 = {-2,-1,1,2,2,1,-1,-2};
	static int[] dc1 = {1,2,2,1,-1,-2,-2,-1};
	//4방향탐색 (상하좌우)
	static int[] dr2 = {-1,1,0,0};
	static int[] dc2 = {0,0,-1,1};
	
	//Point
	static class Point {
		int r, c;	//행,열
		int count;	//이동횟수
		int k;		//말처럼이동횟수
		public Point(int r, int c, int count, int k) {
			this.r = r;
			this.c = c;
			this.count = count;
			this.k = k;
		}
	}
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		K = sc.nextInt();	//말처럼 이동가능한 횟수
		W = sc.nextInt();	//가로길이(열)
		H = sc.nextInt();	//세로길이(행)
		
		board = new int[H][W];
		visited = new boolean[H][W][K+1];
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				board[i][j] = sc.nextInt();
			}
		}//격자판 입력
		
		bfs();
		
		
	}	//main


	//bfs
	private static void bfs() {
		//(0,0) -> (H-1,W-1) 이동
		//'1' 장애물 이동 불가
		//'0' 이동가능 (시작점, 도착점 항상 '0')
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0, 0));	//	(0,0) / 이동횟수0 / 말처럼이동0
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			Point now = q.poll();
			//도착
			if (now.r == H - 1 && now.c == W - 1) {
				//이동횟수 출력 후 종료.
				System.out.println(now.count);
				return;
			}
			
			////일반이동과 말처럼이동 함께 고려
			//일반이동
			for (int i=0; i<4; i++) {
				int nr = now.r + dr2[i];
				int nc = now.c + dc2[i];
				//유효범위체크
				if(nr < 0 || nc < 0 || nr >= H || nc >= W) continue;
				//	이동가능지점 / 방문X
				if(board[nr][nc] == 0 && !visited[nr][nc][now.k]) {
					visited[nr][nc][now.k] = true;
					q.add(new Point(nr, nc, now.count + 1, now.k));
				}
			}//일반이동
			//말처럼이동 (K가 남아있을때만)
			if(now.k < K) {
				for(int i=0; i<8; i++) {
					int nr = now.r + dr1[i];
					int nc = now.c + dc1[i];
					//유효범위체크
					if(nr < 0 || nc < 0 || nr >= H || nc >= W) continue;
					//	이동가능지점 / 말처럼방문X
					if(board[nr][nc] == 0 && !visited[nr][nc][now.k + 1]) {
						visited[nr][nc][now.k + 1] = true;
						q.add(new Point(nr, nc, now.count + 1, now.k + 1));
					}
				}
			}//말처럼이동
			
		} // while
		
		//Queue 비었어도 도착X -> -1 출력
		System.out.println(-1);
	}	//bfs
	
	
}	//class
