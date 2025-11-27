package N1181;

import java.util.*;
import java.io.*;

public class N1181_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 중복 제거를 위해 Set 사용
		Set<String> S = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			S.add(br.readLine());
		}
		
		// Set -> List (정렬을 위해)
		List<String> L = new ArrayList<>(S);
		
		// 정렬 
		Collections.sort(L, (a, b) -> {
			// 길이 같으면 사전 순
			if (a.length() == b.length())
				return  a.compareTo(b);
			// 길이 다르면 길이 순
			return a.length() - b.length();
		});
		
		for (String s : L) {
			System.out.println(s);
		}
	}
}
