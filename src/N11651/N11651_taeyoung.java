package N11651;

import java.util.*;
import java.io.*;

public class N11651_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		// A[y] -> y좌표에 대한 x 리스트
		// 인덱스 0부터 -> A[i]: y=i-100000
		List<Integer>[] A = new List[200001];
		for (int i = 0; i <= 200000; i++) {
			A[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			A[y+100000].add(x);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i <= 200000; i++) {
			if(A[i].isEmpty()) // 해당 y좌표인 점 없으면 넘어기기
				continue;
			
			Collections.sort(A[i]); // x좌표 순으로 정렬
			
			for (int x : A[i]) {
				sb.append(x).append(" ").append(i-100000).append("\n");
			}
		}
		System.out.println(sb);
	}
}

