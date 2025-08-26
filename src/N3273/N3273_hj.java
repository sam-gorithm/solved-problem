package N3273;

import java.util.Scanner;

public class N3273_hj {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 배열 크기 N
		int N = sc.nextInt();
				
		// 숫자 배열 입력
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 합이 X가 되는 순차적 두 수 찾기
		int X = sc.nextInt();

		int res = 0;
		int[] freq = new int[X];	// 합쳐서 X가 되는 숫자는 0-X까지
		
		// 두 수 찾기
		for (int i = 0; i < N; i++) {	
			if (X-arr[i]>0 && X-arr[i]<X) {	// 인덱스가 유효한 경우
				if (freq[X-arr[i]]==1) {	// 합이 X가 되는 값이 존재하면
					res++;					// 정답 카운트++
				}
				freq[arr[i]]++;
			}
		}
		
		System.out.println(res);
	}
}
