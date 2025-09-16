package N5427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N5427_geonnam {
	static int W,H;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0}; 
	static int[] dc = {0,0,-1,1}; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine()); //테스트 케이스
		
		for(int tc=1;tc<=t;tc++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			visited = new boolean[H][W]; //방문 체크 배열
			
			for(int i=0;i<H;i++) {
				String s = br.readLine();
				map[i] = s.toCharArray();
			}
			int result = bfs();
			
			if(result == -1)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(result);
		}
	}
	
	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> fire = new LinkedList<>();
		
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(map[i][j] == '*') { //불이면 fire 큐에 다 집어 넣는다.
					fire.add(new int[] {i,j});
				}else if(map[i][j] == '@') { //상근이 위치는 q에 집어 넣는다
					q.add(new int[] {i,j,0});
					visited[i][j] = true;
				}
			}
		}
		
		while(!q.isEmpty()) {
			//불이 붙으려는 칸도 상근이가 이동할 수 없으므로 불부터 이동
			int fsize = fire.size();
			for(int i=0;i<fsize;i++) {
				int[] b = fire.poll();
				
				for(int d=0;d<4;d++) { //4방향 탐색
					int nr = b[0] + dr[d];
					int nc = b[1] + dc[d];
					
					if(nr<0||nc<0||nr>=H||nc>=W) continue;
					if(map[nr][nc] == '#' || map[nr][nc] == '*') continue; //벽이면 불이 붙지 못함
					
					map[nr][nc] = '*';
					fire.add(new int[] {nr,nc});
				}
			}
			
			int qsize = q.size(); //상근이
			for(int i=0;i<qsize;i++) {
				int[] cur = q.poll();
				int count = cur[2]; //상근이 이동 횟수
				
				for(int d=0;d<4;d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					
					//배열 범위를 벗어나면 탈출한 것이다.
					if(nr<0||nc<0||nr>=H||nc>=W) {
						return count+1;
					}
					if(map[nr][nc] != '.') continue; //상근이가 이동할 수 없는 곳이면 안간다.
					if(visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					q.add(new int[] {nr,nc,count+1});
				}
			}
		}
		return -1;
	}
}
