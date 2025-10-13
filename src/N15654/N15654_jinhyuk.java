package N15654;

import java.util.Arrays;
import java.util.Scanner;

//	N과 M (5) / 실버 3 / 1996ms
public class N15654_jinhyuk {
	//static
	static int N, M;
	static int[] num;
	static int[] arr;
	static boolean[] check;


	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();

		num = new int[N];
		arr = new int[M];
		check = new boolean[N];

		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		//사전 순으로 증가하는 순서로 출력하기 위해 정렬 및 재귀
		Arrays.sort(num);
		func(0);
		
	}	//main
	
	
	//func
	public static void func(int depth) {
		
		//기저조건
		if (depth == M) {
			//M개 출력 후 종료
			for (int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}

		//재귀파트
		for (int i = 0; i < N; i++) {
			//i번째 수 사용하지 않았다면
			if (!check[i]) {
				//사용표시 후 재귀
				check[i] = true;
				arr[depth] = num[i];
				func(depth + 1);
				//다음 경우를 위해 되돌리기
				check[i] = false;
			}
		}
		
	}	//func
	
	
}	//class