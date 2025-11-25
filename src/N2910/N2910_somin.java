package N2910;

import java.util.*;

public class N2910_somin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int c = sc.nextInt();

		Map<Integer, Integer> map = new LinkedHashMap<>();

		for (int i = 0; i < n; i++) {
			int num = sc.nextInt();
			if (map.containsKey(num)) {
				// 있을 경우 기존 값 가져와서 1 더함
				map.put(num, map.get(num) + 1);
			} else {
				// 없을 경우 1을 넣음
				map.put(num, 1);
			}
		}

		List<Integer> list = new ArrayList<>(map.keySet());

		// 정렬
		// 빈도수가 같으면 원래 순서 유지
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				// 빈도수 기준 내림차순 정렬
				return map.get(b) - map.get(a);
			}
		});

		for (int num : list) {
			int count = map.get(num);
			while (count-- > 0) {
				System.out.print(num + " ");
			}
		}
	}
}
