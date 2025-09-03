package N10773;

import java.util.Scanner;
import java.util.Stack;

public class N10773_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//K번 연산 수행
		int K = sc.nextInt();
		
		//정수를 담을 스택
		Stack<Integer> st = new Stack<>();
		int ans = 0;
		
		//K번 연산
		for(int i=0; i<K; i++) {
			
			//정수가 "0"일 경우에 지울 수 있는 수가 있음을 보장할 수 있다. isempty() 확인 필요 X
			int num = sc.nextInt();
			if (num == 0) {
				// 0 이라면 스택에서 최근 입력한 정수를 제거
				st.pop();
			}else {
				// 0 아니라면 스택에 입력받은 정수 담기
				st.push(num);
			}
			
		}	//K번 연산
		
		//결과출력
		//스택에 정수가 없을 때까지 꺼내서 ans에 더해줌
		while(!st.isEmpty()) {
			ans += st.pop();
		}
		//합산된 ans가 정답
		System.out.println(ans);
		
		
	}	//main

}	//class
