package N5397;

import java.util.Scanner;
import java.util.Stack;

public class N5397_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//테스트 케이스 수
		int T = sc.nextInt();
		
		//테스트 케이스
		for(int tc=1; tc<=T; tc++) {
			
			//명령어
			String str = sc.next();
			
			//좌, 우 커서 이동을 구현한 스택
			Stack<Character> left = new Stack<>();
			Stack<Character> right = new Stack<>();
			
			//명령어 수행
			for(int i=0; i<str.length(); i++) {
				//해당 명령어
				char ch = str.charAt(i);
				
				// '-'라면 커서 바로 앞에 글자가 존재할 때, 그 글자를 지운다.
				if (ch == '-') {
					if(!left.isEmpty())
						left.pop();
				}
				// '<'라면 왼쪽으로 이동할 수 있다면 이동
				else if (ch == '<') {
					if(!left.isEmpty())
						right.push(left.pop());
				}
				// '>'라면 오른쪽으로 이동할 수 있다면 이동
				else if (ch == '>') {
					if(!right.isEmpty())
						left.push(right.pop());
				}
				// 그 외 문자를 입력하는 부분
				else {
					left.push(ch);
				}
				
			}	//for-i
			
			
			//결과출력
			//스택에서 꺼낸 문자들을 합치기 위한 Stringbuilder 객체 sb 생성
			StringBuilder sb = new StringBuilder();
			//left 스택이 빌 때까지 오른쪽 스택에 넣기
			while(!left.isEmpty()) {
				right.push(left.pop());
			}
			//right 스택이 빌 때까지 꺼내서 sb에 추가
			while(!right.isEmpty()) {
				sb.append(right.pop());
			}
			//sb출력
			System.out.println(sb);
			
		}	//tc
		
		
		
	}	//main

}	//class
