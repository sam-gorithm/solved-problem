package N15652;

import java.util.Scanner;

//	N과 M (4) / 실버 3 / 116ms
public class N15652_jinhyuk {
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
		func(1, 0);
		System.out.println(sb);

		
	}	//main
	
	
	//func
	private static void func(int k, int depth) {
		
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
		for(int i=k; i<=N; i++) {
			arr[depth] = i;
			//비내림차순 (다음 수는 같거나 큰 수) 
			func(i, depth+1);
		}
		
	}	//func

	
}	//class
