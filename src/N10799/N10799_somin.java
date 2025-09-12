import java.util.Scanner;
import java.util.Stack;

public class N10799_somin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int L = str.length();

		Stack<Character> st = new Stack<>();

		int cnt = 0; // 쇠막대기 조각의 총 개수 

		for (int i = 0; i < L; i++) {
			char curChar = str.charAt(i);
			
			// '(' 일 경우 막대기 시작 
			if (curChar == '(') {
				st.push(curChar);
			}
			
			// ')' 일 경우 짝이 되는 '(' 존재 
			else {
				st.pop();
				char prevChar = str.charAt(i - 1);
				
				// 이전 문자가 '(' 인 경우 레이저를 의미 
				if (prevChar == '(') {
					cnt += st.size();
					
				// 이전 문자가 ')' 인 경우 막대기의 끝을 의미 	
				} else {
					cnt += 1;
				}
			}

		}

		System.out.println(cnt);
	}
}