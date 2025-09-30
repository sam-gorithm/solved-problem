package N1992;

import java.util.Scanner;

//	쿼드트리 / 실버 1 / 180ms
public class N1992_jinhyuk {
	//static
	static int[][] paper;
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		paper = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String line = sc.next();
			for(int j=0; j<N; j++) {
				paper[i][j] = line.charAt(j) - '0';
			}
		}//입력
		
		//재귀
		select(0, 0, N);

		
	}	//main


	//select
	private static void select(int r, int c, int N) {
		
		////기저조건
		//isValid : 1 ~ 9 각 구역이 nextSize x nextSize 만큼 같은 색인지 확인하는 함수
		if(isValid(r, c, N)) {
			if(paper[r][c] == 0	) {
				System.out.print(0);
			}else {
				System.out.print(1);
			}
			
			return;
		}
		
		////재귀파트
		//다음 N의 크기
		int nextSize = N/2;	//	ex) 4 -> 2 -> 1 끝.
		
		System.out.print("(");
		
		select(r, c, nextSize);							//1		//1 2
		select(r, c + nextSize, nextSize);				//2		//3 4 순서대로
		select(r + nextSize, c, nextSize);				//3
		select(r + nextSize, c + nextSize, nextSize);	//4
		
		System.out.print(")");
		
	}	//select


	//isValid
	private static boolean isValid(int r, int c, int N) {
		
		int start = paper[r][c];
		
		for(int i=r; i<r+N; i++) {
			for(int j=c; j<c+N; j++) {
				//하나라도 다른 색 있다면 false
				if(paper[i][j] != start) {
					return false;
				}
			}
		}
		
		//모든 구역이 같은 색이라면 true
		return true;
		
	}	//isValid

}	//class
