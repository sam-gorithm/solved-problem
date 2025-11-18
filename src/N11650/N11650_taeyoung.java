package N11650;

import java.util.*;
import java.io.*;

public class N11650_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		// A[x] -> x좌표에 대한 y 리스트
		// 인덱스 0부터 -> A[i]: x=i-100000
		List<Integer>[] A = new List[200001]; 
		for (int i = 0; i <= 200000; i++) {
			A[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			A[x+100000].add(y);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i <= 200000; i++) {
			if(A[i].isEmpty()) // 해당 x좌표인 점 없으면 넘어기기
				continue;
			
			Collections.sort(A[i]); // y좌표 순으로 정렬
			
			for (int y : A[i]) {
				sb.append(i-100000).append(" ").append(y).append("\n");
			}
		}
		System.out.println(sb);
	}
}

