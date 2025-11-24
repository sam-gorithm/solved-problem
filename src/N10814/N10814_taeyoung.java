package N10814;

import java.util.*;
import java.io.*;

public class N10814_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		List<String>[] A = new List[201]; // A[age] -> 나이가 age인 사람 리스트
		for (int i = 1; i <= 200; i++) {
			A[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			A[age].add(name);
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 나이 증가하는 순으로
		for (int age = 1; age <= 200; age++) {
			if(A[age].isEmpty()) // 해당 나이 없으면 넘어가기
				continue;
			
			// (먼저 가입-> 리스트에 추가된 순서)
			for (String name : A[age]) {
				sb.append(age).append(" ").append(name).append("\n");
			}
		}
		System.out.println(sb);
	}
}

