package N2146;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//	다리 만들기 / 골드 3 / 360ms
public class N2146_jinhyuk {
	//static
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int islandId = 2;	//2부터 섬번호 부여
	static int minBridge = Integer.MAX_VALUE;	//최소 다리길이
	
	
	//4방향탐색 (상하좌우)
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	//Point 객체
	static class Point {
		int r,c;	//행,열
		int dist;	//거리
		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}//맵 입력
		
		//1. bfs1() : 각 섬마다 bfs 수행 후 섬번호 부여 (2, 3, 4, ...)
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs1(i,j);
					islandId++;	//다음 섬번호 증가
				}
			}
		}
		
		//2. bfs2 : 각 섬마다 바다 건너서 다른 섬에 도착시 다리길이 반환
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] >= 2) {
					bfs2(i, j, map[i][j]);	//행, 열, 해당섬번호
					//bfs2가 종료될 때마다 섬마다 최소다리길이(minBride)가 반환됨.
				}
			}
		}
		
		//결과출력 (최소다리길이)
		System.out.println(minBridge);
	}	//main


	//bfs1 : 각 섬마다 섬번호 부여
	private static void bfs1(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c, 0));
		visited[r][c] = true;
		map[r][c] = islandId;	//해당 섬번호로 초기화
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				//유효범위체크
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				//	같은섬 / 방문X
				if(map[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new Point(nr, nc, 0));
					map[nr][nc] = islandId;	//같은섬이므로 해당 섬번호로 초기화
				}
			}//for
			
		}//while
		
	}	//bfs1

	
	//bfs2 : 각 섬마다 바다 건너 다른 섬까지 다리 연결
	private static void bfs2(int r, int c, int startIsland) {
		//Queue와 *visited 배열 초기화
		Queue<Point> q = new LinkedList<>();
		visited = new boolean[N][N];
		
		q.add(new Point(r, c, 0));
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(now.dist >= minBridge) continue;	//백트래킹, 다른섬에서 구한 최소다리길이보다 길면 더 이상 진행X
			
			for(int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				//유효범위체크
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				//종료조건 : 바다X / 다른섬 / 방문X
				if(map[nr][nc] != 0 && map[nr][nc] != startIsland && !visited[nr][nc]) {
					//현재길이와 다른섬에서 구한 최소다리길이 비교후 반환
					minBridge = Math.min(minBridge, now.dist);
					return;
				}
				//bfs 진행 조건 : 바다 / 방문X
				if(map[nr][nc] == 0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new Point(nr, nc, now.dist + 1));
				}
			}
		}//while
		
	}	//bfs2
	
	
}	//class
