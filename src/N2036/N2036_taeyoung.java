package N2036;

import java.util.*;
import java.io.*;

public class N2036_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		List<Integer> pos = new ArrayList<>(); // 1보다 큰 수 저장
		List<Integer> neg = new ArrayList<>(); // 1보다 작은 수 저장
		
		long ans = 0;
		
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if (x > 1) pos.add(x);
			else if (x == 1) ans += 1; // 1은 곱하지 않고 그냥 더하기
			else neg.add(x);
		}
		
		// 정렬
		Collections.sort(pos);
		Collections.sort(neg);
		
		// 1 초과 수 -> 큰 순서대로 두개씩 곱해서 더하기 (1개 남으면 그냥 더하기)
		while (!pos.isEmpty()) {
			// 큰 순서 -> 리스트 뒤에서부터 제거
			long x = pos.remove(pos.size()-1);
			if (!pos.isEmpty()) {
				x *= pos.remove(pos.size()-1);
			}
			ans += x;
		}
		
		// 1 미만 수 -> 작은 순서대로 두개씩 곱해서 더하기 (1개 남으면 그냥 더하기)
		while (!neg.isEmpty()) {
			// 작은 순서 -> 리스트 앞에서부터 제거
			long x = neg.remove(0);
			if (!neg.isEmpty()) {
				x *= neg.remove(0);
			}
			ans += x;
		}
		
		System.out.println(ans);
	}

}
