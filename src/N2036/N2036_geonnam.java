package N2036;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class N2036_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int [n];
		
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		long result = 0; //int 값 벗어나니까 long으로
		
		Arrays.sort(arr);
		boolean[] visited = new boolean[n];
		
		//음수일때만 먼저 계산
		for(int i=0;i<arr.length;i++) {
			if(arr[i] < 0) {
				if(visited[i]) continue;
				
				//음수 2개일 때
				if(i+1 < n && arr[i+1] <= 0) {
					visited[i] = true;
					visited[i+1]= true;
					result += (long) arr[i]*arr[i+1];
				}
				//음수 하나에 다음꺼 양수면 음수 더해버리기
				else if(i+1 < n && arr[i+1] > 0){
					visited[i] = true;
					result += arr[i];
					break; //양수 나왔으니까 더 해볼 필요 X
				}
			}
			//양수 나왔으면 더 해볼 필요가 없다.
			else {
				break;
			}
		}
		
		//양수일때
		for(int i=n-1;i>=0;i--) {
			if(arr[i]>0) {
				if(visited[i]) continue;
				
				// 둘 다 양수이면서 1이 아닐때
				if(i > 0 && arr[i-1] > 1 && !visited[i-1]) {
					visited[i] = true;
					visited[i-1]= true;
					result += (long) arr[i]*arr[i-1];
				}
				//1이 포함될 때
				else if(i > 0 && arr[i-1] >= 0 && !visited[i-1]){
					visited[i] = true;
					visited[i-1] = true;
					result += arr[i] + arr[i-1];
				}
				// 다음 수가 음수일때
				else if(i > 0 && arr[i-1] < 0 && !visited[i-1]){
					visited[i] = true;
					result += arr[i];
				}
			}
		}
		
		//혹시 방문 안 한거는 다 더해주기
		for(int i=0;i<arr.length;i++) {
			if(visited[i] == false) {
				result += arr[i];
			}
		}
		
		System.out.println(result);
	}
}
