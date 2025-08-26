package N2577;

import java.util.*;

public class N2577_taeyoung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// A, B, C 입력
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		// A x B x C 계산 결과
		int X = A * B * C;
		
		int[] arr = new int[10]; // arr[i] -> i가 몇 번 쓰였는지
		while (X > 0) { // X가 0보다 크면 계속 반복
			arr[X % 10]++; // 현재 X의 1의 자리 수(X % 10) 카운트
			X /= 10; // X를 10으로 나눔 (다음 자리수 확인을 위함)
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(arr[i]); // 형식에 맞게 출력
		}
	}
}
