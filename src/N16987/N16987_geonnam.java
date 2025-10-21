package N16987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N16987_geonnam {
	static int N; //달걀 개수
	static int[][] arr; //달걀 내구도, 무게
	static int result;//깨진 달걀 수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2]; //달걀 내구도, 무게
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}//입력 값 받기
		
		result = Integer.MIN_VALUE;
		
		dfs(0);
		
		System.out.println(result);
	}
	
	static void dfs(int idx) {
		if(idx == N) {//마지막 계란에 도달했을 때
			int count = 0;
			for(int i=0;i<N;i++) {
				if(arr[i][0] <= 0) count++;
			}//깨진 계란 수 세기
			
			if(result < count) result = count;
			return;
		}
		
		//이미 계란이 깨져있다면 넘어가기
		if(arr[idx][0] <= 0) {
			dfs(idx+1);
			return;
		}
		
		boolean hit = false; //불필요한 재귀를 줄이기 위해
		
		for(int i=0; i<N;i++) {
			if(arr[i][0] <= 0) continue; //칠 계란이 이미 깨져있으면 건너뛰기
			if(i == idx) continue; //자기 자신 건너뛰기
			
			arr[idx][0] -= arr[i][1];
			arr[i][0] -= arr[idx][1];
			hit = true;
			
			dfs(idx+1);
			
			arr[idx][0] += arr[i][1]; //상태 복구
			arr[i][0] += arr[idx][1];
		}
		if(!hit) dfs(idx+1);
	}
}
