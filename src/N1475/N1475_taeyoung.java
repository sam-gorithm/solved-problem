package N1475;

import java.util.*;

public class N1475_taeyoung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] arr = new int[10]; // arr[i] -> 필요한 i의 개수

		// 각 자리수를 확인하여 배열에 입력
		while (N > 0) {
			arr[N % 10]++;
			N /= 10;
		}
		// 6과 9는 뒤집어서 사용 가능해서 하나로 간주한다.
		arr[6] = (arr[6] + arr[9] + 1) / 2;
		// 한 세트에 두 장 들어가서 2로 나눔
		// 정수 나눗셈 연산은 소수점 아래를 버리지만 여기서는 올림한 값이 필요
		// -> 1을 더한 뒤 나눠준다

		int max = 0; // 가장 많이 필요한 숫자 개수 = 필요한 세트 개수
		for (int i = 0; i < 9; i++) {
			max = Math.max(max, arr[i]); // 0~8까지 비교
		}

		System.out.println(max);
	}
}
