package N10799;

import java.util.Scanner;
import java.util.Stack;

//쇠막대기
public class N10799_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        
		//괄호 표현
		String str = sc.next();

		//조각수, 적층유무
		int cnt = 0;
		boolean raser = false;

		Stack<Character> stack = new Stack<>();

		//절단
		for (int i = 0; i < str.length(); i++) {
			// '('라면
			if (str.charAt(i) == '(') {
				stack.push(str.charAt(i));
				raser = true;
			} else { // ')'라면
				stack.pop();
				if (raser) { // 적층되어있음
					cnt += stack.size();
					raser = false;
				} else { // ')'인데(레이저인데) 적층X
					cnt++;
				}
			}
			
		}	//절단
	
		//결과출력
		System.out.println(cnt);
	}	//main
	
	
}
