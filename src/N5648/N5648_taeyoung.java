package N5648;

import java.util.*;
import java.io.*;

public class N5648_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		List<Long> A = new ArrayList<>();

		// n개 채울 때까지 반복
		while (A.size() < n) { 
			// 토큰 없으면, 다음 줄 읽기
			while (!st.hasMoreTokens()) {
				String line = br.readLine();
				if (line == null) // 줄이 비어있는 경우 -> 입력 끝
					break;
				st = new StringTokenizer(line); // 다음 줄 토큰화
			}

			String s = st.nextToken(); // 다음 수(string)

			// 문자를 뒤집어서 long으로 변환
			StringBuilder sb = new StringBuilder(s);
			long r = Long.parseLong(sb.reverse().toString());

			A.add(r); // 리스트에 추가
		}

		Collections.sort(A); // 정렬

		for (long a : A) {
			System.out.println(a);
		}
	}
}
