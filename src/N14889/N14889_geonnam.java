package N14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14889_geonnam {
	static int N;
	static int[][] map;
	static boolean[] visited;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0);
		System.out.println(result);
	}
	
	//팀 뽑기
	static void dfs(int idx, int count) {
		//팀을 다 뽑았을 때
		if(count == N/2) {
			//방문 처리 한 애들이 스타트팀이 된다.
			check(); // 능력치 차이 계산
			return;
		}
		
		for(int i=idx;i<N;i++) {
			visited[i] = true; //방문 처리
			dfs(i+1, count+1);
			visited[i] = false; //복구
		}
	}

	static void check() {
		int sSum = 0, lSum = 0; //스타트 점수, 링크 점수
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				//스타트팀
				if(visited[i] && visited[j]) {
					sSum += map[i][j];
				}
				//링크 팀
				else if(!visited[i] && !visited[j]) {
					lSum += map[i][j];
				}
			}
		}
		
		//차이가 result보다 적다면 result에 대입
		if(result > Math.abs(sSum-lSum)) {
			result = Math.abs(sSum-lSum);
		}
	}
}
