package N1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class N1003_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		List<int[]> list = new ArrayList<>();
		
		list.add(new int[] {1,0}); //0번째
		list.add(new int[] {0,1}); //1번째

		/* 
		 * 0 > 10
		 * 1 > 01
		 * 2 > 11
		 * 3 > 12
		 * 4 > 23
		 * 자신의 전과 전전을 합치면 자신의 결과가 나온다는 것을 알 수 있다.
		 */
		
		int T = Integer.parseInt(br.readLine()); //테스트 케이스 수
		
		for(int tc=0;tc<T;tc++) {
			int N = Integer.parseInt(br.readLine()); //N번째
			
			//N이 2보다 작다면 이미 리스트에 들어있으므로 출력에 추가
			if(N < 2) {
				sb.append(list.get(N)[0]).append(" ").append(list.get(N)[1]).append("\n");
				continue;
			}
			
			//이미 리스트에 N번째가 있는지 확인
			if(list.size()-1 < N) {
				//없다면 규칙에 맞게 추가한다.
				for(int i=list.size();i<=N;i++) {
					int num1_0 = list.get(i-2)[0]; 
					int num1_1 = list.get(i-2)[1]; 
					int num2_0 = list.get(i-1)[0]; 
					int num2_1 = list.get(i-1)[1];
					
					list.add(new int[] {num1_0+num2_0, num2_0+num2_1});
				}
			}
			
			sb.append(list.get(N)[0]).append(" ").append(list.get(N)[1]).append("\n");
		}
		System.out.println(sb);
	}
}
