package N2910;

import java.util.*;
import java.io.*;

public class N2910_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		// 숫자와 빈도 저장 -> Map
		// key : 숫자, value : 빈도
		// LinkedHashMap 사용 -> 입력 순서대로 저장
		Map<Integer, Integer> map = new LinkedHashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			
			// 이미 나온 숫자 -> 빈도 1 증가
			if (map.containsKey(x))
				map.replace(x, map.get(x)+1);
			
			// 새로운 숫자 -> 빈도 1로 입력
			else 
				map.put(x, 1);
		}
		
		// Map의 Entry를 List로 변환
		// Entry란? Map에서 키와 값을 하나의 쌍으로 저장하는 객체
		List<Map.Entry<Integer, Integer>> L = new ArrayList<>(map.entrySet());
		
		// 빈도 내림차순 정렬
		L.sort((a, b) -> b.getValue() - a.getValue());
		
		StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> e : L) {
            int k = e.getKey(); // 숫자
            int v = e.getValue(); // 빈도
           
            // 빈도만큼 숫자 추가
            for (int i = 0; i < v; i++) 
            	sb.append(k).append(" ");
        }

        System.out.print(sb);
	}
}
