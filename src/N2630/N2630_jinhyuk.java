package N2630;

import java.util.Scanner;

//	색종이 만들기 / 실버 2 / 264ms
public class N2630_jinhyuk {
	//static
	static int[][] paper;
	static int cnt1, cnt2;	//흰색, 파란색
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		paper = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				paper[i][j] = sc.nextInt();
			}
		}//입력
		
		//재귀
		select(0, 0, N);
		
		//결과출력
		System.out.println(cnt1);	//흰색 개수
		System.out.println(cnt2);	//파란색 개수
		
	}	//main


	//select
	private static void select(int r, int c, int N) {
		
		////기저조건
		//isValid : 1 ~ 4 각 구역이 nextSize x nextSize 만큼 같은 색인지 확인하는 함수
		if(isValid(r, c, N)) {
			//유효하다면 시작시점 색 구역수 1씩 추가하고 종료
			if(paper[r][c] == 0) {
				cnt1++;
			}else {
				cnt2++;
			}
			
			return;
 		}
		
		////재귀파트
		//다음 N의 크기
		int nextSize = N/2;	//	ex) 4 -> 2 -> 1 끝.
		
		select(r, c, nextSize);							//1		//1 2
		select(r, c + nextSize, nextSize);				//2		//3 4 순서대로
		select(r + nextSize, c, nextSize);				//3
		select(r + nextSize, c + nextSize, nextSize);	//4
		
		
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
