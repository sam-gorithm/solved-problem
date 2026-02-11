package N1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class N1600_geonnam {
	static int[][] map;
	static int K,W,H;
	static int[] dr = {-1,1,0,0}; //인접한 칸
	static int[] dc = {0,0,-1,1}; //인접한 칸
	static int[][] dd = {{1,-2}, {2,-1}, {2,1}, {1,2}, {-1,2}, {-2,1}, {-2,-1},{-1,-2}}; // 대각선 이동
	static boolean[][][] visited; //방문 배열
	static int result, flag; //결과값, 도달할 수 있는지
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 말처럼 이동할 수 있는 횟수 K
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		//W: 가로 H: 세로
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		visited = new boolean[H][W][K+1]; // 말처럼 이동했는지 파악하기 위해 3차원
		result = Integer.MAX_VALUE;
		flag = 0; // 도달할 수 있는지 파악을 위함
		
		// map 채우기
		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		//맨 왼쪽 좌표에서 bfs 시작
		bfs(0,0,0,0);
		
		if(flag == 1) 
			System.out.println(result);
		else
			System.out.println(-1);
	}
	
	static void bfs(int x, int y, int kCount, int length) {
		Queue<int[]> q = new ArrayDeque();
		visited[x][y][0]= true;
		q.add(new int[] {x,y,0,0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			//목적지에 도달했을 때 제일 적은 수를 result에 저장
			if(cur[0] == H-1 && cur[1] == W-1) {
				flag = 1;
				result = Math.min(result, cur[3]);
				continue;
			}
			
			//말처럼 이동할 수 있는 기회가 있을때
			if(cur[2] < K) {
				for(int i=0;i<8;i++) {
					int nr = cur[0] + dd[i][0];
					int nc = cur[1] + dd[i][1];
					
					if(nr<0||nc<0||nr>=H||nc>=W) continue;
					if(map[nr][nc] == 1) continue;
					if(visited[nr][nc][cur[2]+1]) continue;
					
					visited[nr][nc][cur[2]+1] = true;
					q.add(new int[] {nr,nc,cur[2]+1, cur[3]+1});
				}
			}
			
			// 인접한 칸으로 이동하는 경우의 수
			for(int i=0;i<4;i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr<0||nc<0||nr>=H||nc>=W) continue;
				if(map[nr][nc] == 1) continue;
				if(visited[nr][nc][cur[2]]) continue;
				
				visited[nr][nc][cur[2]] = true;
				q.add(new int[] {nr,nc,cur[2], cur[3]+1});
			}
		}
	}
}
