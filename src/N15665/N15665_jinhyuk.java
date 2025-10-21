package N15665;

import java.util.Arrays;
import java.util.Scanner;

// N과 M (11) / 실버 2 / 360ms
public class N15665_jinhyuk {
	//static
	static int N, M;
	static int[] num;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		num = new int[N];
		arr = new int[M];
		for(int i=0; i<N; i++) {
			num[i] = sc.nextInt();
		}
		//사전 순으로 증가하는 순서로 출력하기 위해 정렬 및 재귀
		Arrays.sort(num);
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
		int prev_num = 0;	//중복된 수 출력 방지
		for(int i=0; i<N; i++) {
			//해당 숫자 출력하지 않았다면
			if(prev_num != num[i]) {
				arr[depth] = num[i];
				prev_num = num[i];	//중복된 수 출력하지 않도록 초기화
				func(depth+1);
			}
		}
		
	}	//func

	
}	//class
