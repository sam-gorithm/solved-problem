package N2447;

import java.util.Scanner;

//	별 찍기 - 10 / 골드 5 / 280ms
public class N2447_jinhyuk {
	//static
	static char[][] map;
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//우선 공백으로 채워놓기
				map[i][j] = ' ';
			}
		}
		
		
		//재귀
		star(0, 0, N);
		
		//결과출력
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}	//main

	
	//star
	private static void star(int r, int c, int N) {
		
		////기저조건
		if(N == 1) {
			//재귀파트에서 가운데 부분은 호출 생략하고 (빈칸 상태 유지)
			//9개 구역 각 지점의 단위가 1이 될 때까지 반복 호출 후 1이 되면 * 찍은 다음에 종료
			map[r][c] = '*';
			return;
		}
		
		//// 재귀파트
		int nextSize = N/3;
		//재귀마다 9개 구역에 대해
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				//가운데 구역은 재귀 제외
				if(i == 1 && j == 1) continue;
				//그 외 구역 순서대로 재귀 호출
				star(r + nextSize * i, c + nextSize * j, nextSize);
			}
		}
		
	}	//star

	
}	//class
