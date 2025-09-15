package N7562;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//	나이트의 이동 / 실버 1 / 280ms
public class N7562_jinhyuk3 {
	//static
	static int N;
	static int[][] map;
	static int r1, c1, r2, c2;	//출발점, 도착점
	static int ans;
	
	//나이트 이동 가능 방향 (1시 -> 시계방향)
	static int[] dr = {-2,-1,1,2,2,1,-1,-2};
	static int[] dc = {1,2,2,1,-1,-2,-2,-1};
	
	//Point
	static class Point {
		int r,c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}	//Point
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		//tc
		for(int tc=1; tc<=T; tc++) {
			
			N = sc.nextInt();
			
			map = new int[N][N];
			
			r1 = sc.nextInt();	//출발점 행
			c1 = sc.nextInt();	//출발점 열
			r2 = sc.nextInt();	//도착점 행
			c2 = sc.nextInt();	//도착점 열
			
			
			//결과출력
			ans = 0;
			System.out.println(bfs(r1, c1));
		}	//tc
	
		
		
	}	//main


	//bfs
	public static int bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		map[r][c] = 1;	//시작점 이동 거리 1 -> 출력 시 -1 해야함.
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			//Queue에서 뽑은 좌표가 도착점 일 때
			if(now.r == r2 && now.c == c2) {
				ans = map[now.r][now.c] - 1;	//-1 감소시키고 정답 반환
				break;
			}
			//나이트 이동
			for(int i=0; i<8; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				//유효범위 체크
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				//방문하지않았다면 이동 거리 1 증가시키며 이동
				if(map[nr][nc] == 0) {
					map[nr][nc] = map[now.r][now.c] + 1;
					//Queue에 넣고 bfs 이어서 진행
					q.add(new Point(nr, nc));
				}
			}//나이트 이동
		}//while
		
		//정답 반환
		return ans;
	}	//bfs
	
	
}	//class
