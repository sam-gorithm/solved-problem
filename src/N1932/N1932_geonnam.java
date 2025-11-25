package N1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1932_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n]; // 삼각형을 받을 곳
		int[][] result = new int[n][n]; // 각 지점에서의 최댓값을 받을 곳
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<=i;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i<1) result[i][j] = map[i][j];
			}
		}//삼각형을 뒤집은 형태로 입력 받음
		
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<=i;j++) {
				//j기준으로 (i+1,j) , (i+1, j+1)로만 이동할 수 있음
				//첫 번째꺼
				int sum = result[i][j] + map[i+1][j];
				result[i+1][j] = Math.max(result[i+1][j], sum); //최댓값 갱신
				
				//두 번째꺼
				sum = result[i][j] + map[i+1][j+1];
				result[i+1][j+1] = Math.max(result[i+1][j+1], sum); // 최댓값 갱신
			}
		}
		
		int r_result = 0; // 마지막에서 최댓값 구하기
		for(int i=0;i<n;i++) {
			r_result = Math.max(r_result, result[n-1][i]);
		}
		System.out.println(r_result);
	}
}
