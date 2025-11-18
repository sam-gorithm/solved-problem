package N14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class N14500_geonnam {
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {0,0,-1,1}; //상, 하, 좌, 우
	static int[] dc = {-1,1,0,0};
	static int result, max;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //세로
		M = Integer.parseInt(st.nextToken()); //가로
		
		map = new int[N][M];
		visited = new boolean[N][M]; //방문 배열
		max = Integer.MIN_VALUE; // 가지치기를 위해 생성
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
			}
		}//map 채우고 최댓값 알아놓기
		
		result = 0; 
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				visited[i][j] = true;
				dfs(i,j,1,map[i][j]);//처음 위치를 넣고 dfs
				visited[i][j] = false;
				int tSum = tShape(i,j); //t자 모양 파악하기
				if(result < tSum) result = tSum;
			}
		}
		System.out.println(result);
	}
	
	static void dfs(int x, int y, int length, int sum) {
		if(sum+max*(4-length) <= result) return; //가지치기 - 최댓값을 넣어도 result보다 작으면 return
		
		if(length == 4) {
			if(result < sum) {
				result = sum;
				return;
			}
		}
		
		for(int i=0;i<4;i++) { //4방향으로 dfs
			int nr = x + dr[i];
			int nc = y + dc[i];
			
			if(nr<0||nc<0||nr>=N||nc>=M) continue;
			if(visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			dfs(nr, nc, length+1, sum+map[nr][nc]);
			visited[nr][nc] = false;
			
		}
	}
	
	//t자 모양 합 파악하기
	static int tShape(int x, int y) {
		ArrayList<Integer> list = new ArrayList<>();
		int sum = 0;
		
		for(int i=0;i<4;i++) {
			int nr = x+dr[i];
			int nc = y+dc[i];
			
			if(nr<0||nc<0||nr>=N||nc>=M) continue;
			
			list.add(map[nr][nc]);
		}
		
		if(list.size() >=3) {
			int minus = 0; //3일때는 0으로 유지
			//상하좌우 다 있으면 최솟값 제거
			if(list.size()==4) {
				minus = Collections.min(list);
			}
			for(int i=0;i<list.size();i++) {
				sum += list.get(i);
			}
			sum+= map[x][y]; //처음 자기 위치 더하기
			sum -= minus; //4개가 있다면 그 중 최솟값 빼기
		}
		
		return sum;
	}
}
