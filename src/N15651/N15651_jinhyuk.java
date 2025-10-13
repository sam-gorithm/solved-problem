package N15651;

import java.util.Scanner;

//	N과 M (3) / 실버 3 / 404ms
public class N15651_jinhyuk {
	//static
	static int N, M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[M];

		//재귀 및 모든 경우의 수 출력
		func(0);
		System.out.println(sb);

		
	}	//main

	
	//func
	private static void func(int depth) {
		
		//기저조건
		if(depth == M) {
			//M개 출력 후 종료
			for(int i=0; i<M; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		//재귀파트
		for(int i=1; i<=N; i++) {
			arr[depth] = i;
			//같은 수O, 중복O 
			func(depth+1);
		}
		
	}	//func

	
}	//class
