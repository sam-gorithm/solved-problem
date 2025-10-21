package N15650;

import java.util.Scanner;

// N과 M (2) / 실버 3 / 96ms
public class N15650_jinhyuk {
	//static
	static int N, M;
	static int[] arr;
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[M];
		
		func(1, 0);
		
		
	}	//main

	
	//func
	private static void func(int k, int depth) {
		
		//기저조건
		if(depth == M) {
			//M개 숫자 출력 후 종료
			for(int i=0; i<M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
			
		}
		
		//재귀파트
		for(int i=k; i<=N; i++) {
			
			arr[depth] = i;
			//같은 수X, 중복X
			func(i+1, depth+1);
			
		}
		
	}	//func
	
	
}	//class
