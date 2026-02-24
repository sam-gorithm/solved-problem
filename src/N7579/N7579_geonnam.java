package N7579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N7579_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] memory = new int[N]; //비활성화할 때 확보할 수 있는 메모리
		int[] cost = new int[N]; // 비용
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		int t_cost = 0; //cost 총합
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			t_cost += cost[i];
		}
		
		int[] dp = new int[t_cost+1];
		
		/* 0/1 배낭문제면 역순으로 돌자
		 * for(물건 수)
		 * 	for(비용 -> 역순 or 정순)
		 * 		dp[] = max ~
		 */
		for(int i=0;i<N;i++) {
			for(int c = t_cost; c>=cost[i]; c--) {
				dp[c] = Math.max(dp[c], dp[c - cost[i]] + memory[i]);
			}
		}
		
		//앞에서부터 찾으니까 비용이 적은거부터 찾게 되므로 발견되면 return
		for(int c=0; c <= t_cost; c++) {
			if(dp[c] >= M) {
				System.out.println(c);
				return;
			}
		}
	}
}
