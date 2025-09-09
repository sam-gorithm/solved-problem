package N3986;

import java.util.Scanner;
import java.util.Stack;

//좋은 단어
public class N3986_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		int totalCnt = 0;
		//tc
		for(int tc=1; tc<=T; tc++) {
			
			String str = sc.next();
			Stack<Character> st = new Stack<>();
			
			for(int i=0; i<str.length(); i++) {
				char ch = str.charAt(i);
				
				//스택이 비어 있지 않고, 맨 위 글자와 같다면
				if(!st.isEmpty() && st.peek() == ch) {
					st.pop();
				}else {	//그 외
					st.push(ch);
				}
				
			}	//for-i
			
			//각 단어별 연산 후 스택 비어있으면 '좋은 단어'
			if(st.isEmpty()) {
				totalCnt++;
			}
			
		}	//tc
		
		//결과출력
		System.out.println(totalCnt);
	}	//main

}
