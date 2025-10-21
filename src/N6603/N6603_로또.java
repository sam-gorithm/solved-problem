package N6603;

import java.util.Scanner;

//	로또 / 실버 2 / 100ms
public class N6603_로또 {
	//static
	static int k;
	static int[] num;
	static int[] lotto;
	static StringBuilder sb = new StringBuilder();
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		lotto = new int[6];
		
		//테스트케이스 k=0이면 종료
		while(true) {
			
			k = sc.nextInt();
			
			//0이면 종료
			if(k == 0) {
				break;
			}
			
			//0 아니면 k개 숫자 입력받고 재귀
			num = new int[k];
			for(int i=0; i<k; i++) {
				num[i] = sc.nextInt();
			}
			
			//테스트케이스마다 재귀
			func(0, 0);
			sb.append("\n");
			
		}//while
		
		//결과출력
		System.out.print(sb);
	}	//main


	//func
	private static void func(int start, int depth) {
		
		//기저조건
		if(depth == 6) {
			//뽑은 6개 숫자 출력 후 종료
			for(int i=0; i<6; i++) {
				sb.append(lotto[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		//재귀파트
		for(int i=start; i<k; i++) {
			lotto[depth] = num[i];
			//중복피하기 위해 매개변수 둘 다 1씩 증가
			func(i+1, depth+1);
		}
		
	}	//func
	
	
}	//class
