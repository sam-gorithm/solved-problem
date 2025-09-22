package N5427;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//	불 / 골드 4 / 692ms
public class N5427_jinhyuk {
	//static
	static int w, h;		//열, 행
	static char[][] map;	//빌딩
	static int[][] visited;	//상원이 이동거리
	static int[][] fire;	//불 이동거리
	static Queue<Point> q;	//상원이 bfs() 큐
	static Queue<Point> fireQ;	//불 firebfs() 큐
	static int ans;			//정답
	static boolean escape;	//탈출여부
	
	//4방향탐색 (상하좌우)
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	//Point
	static class Point {
		int r, c;

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
			
			w = sc.nextInt();	//너비 -> 열
			h = sc.nextInt();	//높이 -> 행
			
			//테스트 케이스마다 빌딩, 이동관련 배열 초기화 / 큐 초기화
			map = new char[h][w];
			visited = new int[h][w];
			fire = new int[h][w];
			
			q = new LinkedList<>();
			fireQ = new LinkedList<>();
			
			//빌딩 입력 및 벽/상원이/불 동시에 입력받기
			for(int i=0; i<h; i++) {
				String line = sc.next();
				for (int j=0; j<w; j++) {
					map[i][j] = line.charAt(j);
					//벽(#)
					if(map[i][j] == '#') {
						//벽인지점 -1로 초기화
						visited[i][j] = fire[i][j] = -1;
					}
					//상원이(@)
					else if(map[i][j] == '@') {
						//상근이 bfs()를 위해 q에 해당 지점 삽입
						q.add(new Point(i, j));
						visited[i][j] = 1;	//이동거리 1 (경계에 닿았을 때 종료시키면 넘어간거랑 결과 같음)
					}
					//불(*)
					else if(map[i][j] == '*') {
						//불 firebfs()를 위해 fireQ에 해당 지점 삽입
						fireQ.add(new Point(i, j));
						fire[i][j] = 1;
					}
				}
			}//입력
			
			//1. 불부터 bfs 탐색 시간별 이동 공간 확인
			firebfs();
			
			//2. 상근이 bfs 탐색, (방문X / 벽X / 불보다 먼저 도달 가능한 곳) bfs 탐색 -> map 테두리에 가면 탈출
			ans = 0;
			escape = false;
			bfs();
			
			//결과출력
			if(!escape) {
				//탈출못했으면 IMPOSSIBLE 출력
				System.out.println("IMPOSSIBLE");
			}else {
				//탈출했으면 이동거리 출력
				System.out.println(ans);
			}
		}	//tc
		
	}	//main
	
	
	//firebfs (불)
	public static void firebfs() {
		while(!fireQ.isEmpty()) {
			Point now = fireQ.poll();
			//불은 종료조건 필요X 끝까지 번져나가기만하면 됨
//			if(now.r == w || now.r == h || now.c == w || now.r == h) {
//				return;
//			}
			//4방향탐색
			for(int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				//유효범위체크
				if(nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
				//아직 불이 안번진곳이라면
				if(fire[nr][nc] == 0) {
					//이동하면서 거리1 증가, 다음 탐색을 위해 fireQ에 삽입
					fire[nr][nc] = fire[now.r][now.c] + 1;
					fireQ.add(new Point(nr, nc));
				}
			}//4방향탐색
		}//while
		
	}	//firebfs
	
	
	//bfs (상근이)
	public static void bfs() {
		escape = false;
		while(!q.isEmpty()) {
			Point now = q.poll();
			//탈출한 경우
			if(now.r == 0 || now.c == 0 || now.r == h - 1 || now.c == w - 1) {
				//이동거리 ans에 담고, escape true로 바꾸고 break
				ans = visited[now.r][now.c];
				escape = true;
				break;
			}
			//4방향탐색
			for(int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				//유효범위체크
				if(nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
				//방문한지점이라면 continue
				if(visited[nr][nc] != 0) continue;
				//(방문X / 불이번지지않은곳 / 불보다먼저지나가는지점) 이라면
				if(fire[nr][nc] == 0 || fire[nr][nc] > visited[now.r][now.c] + 1) {
					//이동하면서 거리1 증가, 다음 탐색을 위해 q에 삽입
					visited[nr][nc] = visited[now.r][now.c] + 1;
					q.add(new Point(nr, nc));
				}
			}//4방향탐색
		}//while

	}	//bfs
	
	
}	//class
