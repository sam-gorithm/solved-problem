package N3273;

import java.util.*;

public class N3273_taeyoung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];

		boolean[] B = new boolean[1000001]; // B[i] -> 배열에 i가 있으면 true

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt(); // 배열에 입력
			B[arr[i]] = true; // 포함 여부 변경
		}

		int x = sc.nextInt();

		int cnt = 0; // 만족하는 쌍 카운트

		for (int a : arr) { // 배열 순회
			if (x - a < 0 || x - a > 1000000) // 인덱스 범위 벗어나는 경우 넘어간다
				continue;
			if (B[x - a]) { // a에 대해 x-a가 배열에 있다면
				cnt++; // 카운트 1 증가
			}
		}
		System.out.println(cnt / 2); // 쌍의 수를 찾아야 하므로 2로 나눈다
	}
}